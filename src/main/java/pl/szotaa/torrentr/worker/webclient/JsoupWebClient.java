package pl.szotaa.torrentr.worker.webclient;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Jsoup's static method wrapper class.
 *
 * @author Jakub Szota
 */

public class JsoupWebClient implements WebClient {

    @Override
    public Connection connect(String url) {
        return Jsoup.connect(url);
    }
}
