package pl.szotaa.torrentr.worker;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.szotaa.UnitTest;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class ScrapWorkerSetFactoryTest {

    @Test
    public void getSetOfScrapWorkers_correctSetReturned() throws Exception {
        Set<AbstractScrapWorker> set = ScrapWorkerSetFactory.getSetOfScrapWorkers("example");
        assertTrue(containsSpecifiedSubclassOfAbstractScrapWorker(set, ThePirateBayScrapWorker.class));
        assertTrue(containsSpecifiedSubclassOfAbstractScrapWorker(set, One337xScrapWorker.class));
        assertEquals(2, set.size());
    }

    private <T extends AbstractScrapWorker> boolean containsSpecifiedSubclassOfAbstractScrapWorker(Set<AbstractScrapWorker> set, Class<T> type){
        for(AbstractScrapWorker worker : set){
            if(type.isInstance(worker)){
                return true;
            }
        }
        return false;
    }
}
