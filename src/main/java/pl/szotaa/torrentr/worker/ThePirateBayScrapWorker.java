package pl.szotaa.torrentr.worker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.szotaa.torrentr.domain.Result;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * ThePirateBay.org search results scraper.
 *
 * @author Jakub Szota
 */

public class ThePirateBayScrapWorker extends AbstractScrapWorker {

    /**
     * Search engine URL.
     */

    //TODO:  TPBSW unit tests

    private static final String ENGINE_URL = "https://thepiratebay.org/search/";

    ThePirateBayScrapWorker(String searchQuery) {
        super(searchQuery);
    }

    @Override
    protected Set<Result> scrap() throws Exception {
        String scrapUrl = buildSearchUrl(super.searchQuery);
        Document searchEngineResponse = Jsoup.connect(scrapUrl).get();
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
        return element.select(".detLink").attr("href");
    }

    private int scrapSeeds(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(2).text());
    }

    private static int scrapPeers(Element element){
        return Integer.parseInt(element.getElementsByTag("td").get(3).text());
    }

    private static double scrapSize(Element element){
        String description = element.select("font").text();
        return 0; //TODO: size scraping with respect to MiB and GiB..
    }
}
