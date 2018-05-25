package pl.szotaa.torrentr.worker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.szotaa.torrentr.domain.Result;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Abstract class representing torrent search engine scraping task. Each implementing class should represent
 * scrap job of one and only one search engine.
 *
 * @author Jakub Szota
 */

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class AbstractScrapWorker implements Callable<Set<Result>> {

    /**
     * Search query requested by user.
     */

    protected final String searchQuery;

    /**
     * Scraps the website for search engine's query search results. Protected access level to prevent direct execution.
     *
     * @return Set of query search results.
     */

    protected abstract Set<Result> scrap() throws Exception;

    /**
     * Provides concurrent execution.
     *
     * @return Set of query search results.
     * @throws Exception
     */

    @Override
    public final Set<Result> call() throws Exception {
        return scrap();
    }
}
