package pl.szotaa.torrentr.worker;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.worker.util.FileSizeConverter;
import pl.szotaa.torrentr.worker.webclient.WebClient;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class One337xScrapWorker extends AbstractScrapWorker {

    private static final String WEBSITE_URL = "http://1337x.to";
    private static final String ENGINE_URL = "http://1337x.to/search/";

    One337xScrapWorker(String searchQuery, WebClient webClient) {
        super(searchQuery, webClient);
    }

    @Override
    protected Set<Result> scrap() throws Exception {
        String scrapUrl = buildSearchUrl(super.searchQuery);
        Document searchEngineResponse = super.webClient.connect(scrapUrl).get();
        Elements resultElements = searchEngineResponse.select("table > tbody > tr");
        return buildResultSet(resultElements);
    }

    private String buildSearchUrl(String searchQuery){
        StringJoiner stringJoiner = new StringJoiner("+", ENGINE_URL, "/1/");
        Arrays.stream(searchQuery.split("\\s+")).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private Set<Result> buildResultSet(Elements resultElements) throws Exception {
        Set<Result> results = new HashSet<>();
        for(Element element : resultElements){
            Result result = Result.builder()
                    .title(scrapTitle(element))
                    .magnetLink(scrapMagnetLink(element))
                    .infoLink(scrapInfoLink(element))
                    .seeds(scrapSeeds(element))
                    .peers(scrapPeers(element))
                    .size(scrapSize(element))
                    .source(WEBSITE_URL)
                    .build();

            results.add(result);
        }
        return results;
    }

    private String scrapTitle(Element element){
        return element.getElementsByTag("a").get(1).text();
    }

    private String scrapInfoLink(Element element){
        return WEBSITE_URL + element.getElementsByTag("a").get(1).attr("href");
    }

    private String scrapMagnetLink(Element element) throws Exception {
        Document torrentInfo = super.webClient.connect(scrapInfoLink(element)).get();
        return torrentInfo.getElementsByTag("ul").get(4).select("li > a").attr("href");
    }

    private int scrapSeeds(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(1).text());
    }

    private int scrapPeers(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(2).text());
    }

    private double scrapSize(Element element){
        String stringSize = element.getElementsByTag("td").get(4).ownText();
        Double size = Double.parseDouble(stringSize.replaceAll("[^\\d.]", ""));
        String unit = stringSize.replaceAll("[\\d.\\s]", "");
        return FileSizeConverter.toKb(size, unit);
    }
}
