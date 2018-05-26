package pl.szotaa.torrentr.service;

import org.springframework.stereotype.Service;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.worker.AbstractScrapWorker;
import pl.szotaa.torrentr.worker.ScrapWorkerSetFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Service providing search functionality.
 *
 * @author Jakub Szota
 */

@Service
public class SearchService {

    /**
     * Provides concurrent scraping.
     */

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * Search all supported search engines, scraps results and combines them into set.
     *
     * @param query Search query.
     * @return Set of results from different search engines.
     */

    public Set<Result> search(String query){ //TODO: cleanup
        Set<AbstractScrapWorker> scrapWorkers = ScrapWorkerSetFactory.getSetOfScrapWorkers(query);
        List<Future<Set<Result>>> listOfSets = null;
        Set<Result> results = Collections.synchronizedSet(new HashSet<>());
        try {
            listOfSets = executorService.invokeAll(scrapWorkers);
            listOfSets.parallelStream().forEach(x -> {
                try{
                    results.addAll(x.get());
                }
                catch (Exception e){

                }
            });
        }
        catch (Exception e){

        }
        return results;
    }
}
