package pl.szotaa.torrentr.worker;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.szotaa.UnitTest;

import java.util.Set;

import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class ScrapWorkerSetFactoryTest {

    @Test
    public void getSetOfScrapWorkers_correctSetReturned() throws Exception {
        Set<AbstractScrapWorker> set = ScrapWorkerSetFactory.getSetOfScrapWorkers("example");
        assertTrue(containsThePirateBayScrapWorker(set));
    }

    private boolean containsThePirateBayScrapWorker(Set<AbstractScrapWorker> set){
        for(AbstractScrapWorker worker : set){
            if(worker instanceof ThePirateBayScrapWorker){
                return true;
            }
        }
        return false;
    }
}