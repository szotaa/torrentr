package pl.szotaa.torrentr.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szotaa.UnitTest;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.worker.AbstractScrapWorker;
import pl.szotaa.torrentr.worker.ScrapWorkerSetFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(PowerMockRunner.class)
@Category(UnitTest.class)
@PrepareForTest(ScrapWorkerSetFactory.class)
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    @Ignore //todo: fix
    public void search_resultsAreFound() throws Exception {
        //given
        AbstractScrapWorker scrapWorker = PowerMockito.mock(AbstractScrapWorker.class);
        Set<Result> results = new HashSet<>(Arrays.asList(
                Result.builder().magnetLink("magnetLink1").build(),
                Result.builder().magnetLink("magnetLink2").build(),
                Result.builder().magnetLink("magnetLink3").build()
        ));
        PowerMockito.when(scrapWorker.call()).thenReturn(results);
        Set<AbstractScrapWorker> workers = new HashSet<>();
        workers.add(scrapWorker);
        PowerMockito.mockStatic(ScrapWorkerSetFactory.class);
        PowerMockito.when(ScrapWorkerSetFactory.getSetOfScrapWorkers(Mockito.anyString())).thenReturn(workers);
        System.out.println(searchService.search("").size());

    }

}