package pl.szotaa.torrentr.worker;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.szotaa.torrentr.worker.webclient.JsoupWebClient;

import java.util.HashSet;
import java.util.Set;

/**
 * Factory for instantiating sets of concrete ScrapWorkers.
 *
 * @author Jakub Szota
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapWorkerSetFactory {

    public static Set<AbstractScrapWorker> getSetOfScrapWorkers(String searchQuery){ //TODO: specify which should be returned through enum??
        //temporary implementation
        Set<AbstractScrapWorker> scrapWorkers = new HashSet<>();
        scrapWorkers.add(new ThePirateBayScrapWorker(searchQuery, new JsoupWebClient()));
        scrapWorkers.add(new One337xScrapWorker(searchQuery, new JsoupWebClient()));
        scrapWorkers.add(new TorLockScrapWorker(searchQuery, new JsoupWebClient()));
        return scrapWorkers;
    }
}
