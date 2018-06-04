package pl.szotaa.torrentr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.worker.AbstractScrapWorker;
import pl.szotaa.torrentr.worker.ScrapWorkerSetFactory;

import java.util.ArrayList;
import java.util.Arrays;
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

@Slf4j
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
     * @return Sorted (by seeds) list of results from different search engines.
     */

    public List<Result> search(String query){ //TODO: cleanup
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
                    log.error(e.getMessage());
                    Arrays.stream(e.getSuppressed()).forEach(throwable -> log.error(throwable.getMessage()));
                }
            });
        }
        catch (Exception e){
            log.error(e.getMessage());
            Arrays.stream(e.getSuppressed()).forEach(throwable -> log.error(throwable.getMessage()));
        }
        return resultSetToSortedList(results);
    }

    private List<Result> resultSetToSortedList(Set<Result> set){
        List<Result> results = new ArrayList<>(set);
        results.sort(Collections.reverseOrder());
        return results;
    }
}
