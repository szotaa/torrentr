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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ThePirateBay.org search results scraper.
 *
 * @author Jakub Szota
 */

public class ThePirateBayScrapWorker extends AbstractScrapWorker {

    private static final String WEBSITE_URL = "https://thepiratebay.org";
    private static final String ENGINE_URL = "https://thepiratebay.org/search/";

    ThePirateBayScrapWorker(String searchQuery, WebClient webClient) {
        super(searchQuery, webClient);
    }

    @Override
    protected Set<Result> scrap() throws Exception {
        String scrapUrl = buildSearchUrl(super.searchQuery);
        Document searchEngineResponse = super.webClient.connect(scrapUrl).get();
        Elements resultElements = searchEngineResponse.select("table > tbody > tr").not(".header");
        return buildResultSet(resultElements);
    }

    private String buildSearchUrl(String searchQuery){
        StringJoiner stringJoiner = new StringJoiner("%20", ENGINE_URL, "/");
        Arrays.stream(searchQuery.split("\\s+")).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private Set<Result> buildResultSet(Elements resultElements){
        Set<Result> results = new HashSet<>();
        for(Element element: resultElements){
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

    private String scrapTitle(Element element) {
        return element.select(".detLink").text();
    }

    private String scrapMagnetLink(Element element) {
        return element.getElementsByTag("a").get(3).attr("href");
    }

    private String scrapInfoLink(Element element){
        return WEBSITE_URL + "/" + element.select(".detLink").attr("href");
    }

    private int scrapSeeds(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(2).text());
    }

    private int scrapPeers(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(3).text());
    }

    private double scrapSize(Element element){
        String description = element.select("font").text();
        description = description.substring(description.indexOf(",") + 7, description.lastIndexOf(","));
        Double size = Double.parseDouble(description.replaceAll("[^\\d.]", ""));
        String unit = description.replaceAll("[\\d.\\s]", "");
        return FileSizeConverter.toKb(size, unit);
    }
}
