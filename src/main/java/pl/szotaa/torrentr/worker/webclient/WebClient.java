package pl.szotaa.torrentr.worker.webclient;

import org.jsoup.Connection;

/**
 * Helper interface for improving testability.
 *
 * @author Jakub Szota
 */

public interface WebClient {

    /**
     * Returns Connection from specified URL.
     * @param url URL you would like to connect to.
     * @return Connection with specified URL.
     */

    Connection connect(String url);
}
