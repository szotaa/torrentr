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

public class TorLockScrapWorker extends AbstractScrapWorker {

    private static final String WEBSITE_URL = "https://www.torlock.com";
    private static final String ENGINE_URL = "https://www.torlock.com/all/torrents/";

    TorLockScrapWorker(String searchQuery, WebClient webClient) {
        super(searchQuery, webClient);
    }

    @Override
    protected Set<Result> scrap() throws Exception {
        String scrapUrl = buildSearchUrl(super.searchQuery);
        Document searchEngineResponse = super.webClient.connect(scrapUrl).get();
        Elements resultElements = searchEngineResponse.select(".panel-default > table > tbody > tr");
        return buildResultSet(resultElements);
    }

    private String buildSearchUrl(String searchQuery){
        StringJoiner stringJoiner = new StringJoiner("-", ENGINE_URL, ".html");
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
        return element.select("td > div > a").text();
    }

    private String scrapInfoLink(Element element){
        return WEBSITE_URL + element.select("td > div > a").attr("href");
    }

    private String scrapMagnetLink(Element element) throws Exception {
        Document torrentInfo = super.webClient.connect(scrapInfoLink(element)).get();
        return torrentInfo.select(".fa-magnet").parents().attr("href");
    }

    private int scrapSeeds(Element element){
        return Integer.parseInt(element.select(".tul").text());
    }

    private int scrapPeers(Element element){
        return Integer.parseInt(element.select(".tdl").text());
    }

    private double scrapSize(Element element){
        String stringSize = element.select(".ts").get(0).ownText();
        Double size = Double.parseDouble(stringSize.replaceAll("[^\\d.]", ""));
        String unit = stringSize.replaceAll("[\\d.\\s]", "");
        return FileSizeConverter.toKb(size, unit);
    }
}
