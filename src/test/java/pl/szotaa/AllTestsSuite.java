package pl.szotaa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.szotaa.torrentr.config.MvcConfigTest;
import pl.szotaa.torrentr.controller.SearchControllerTest;
import pl.szotaa.torrentr.domain.ResultTest;
import pl.szotaa.torrentr.worker.ScrapWorkerSetFactoryTest;
import pl.szotaa.torrentr.worker.ThePirateBayScrapWorkerTest;
import pl.szotaa.torrentr.worker.util.FileSizeConverterTest;

/**
 * Running this suite executes all tests. Every new test should be listed here in @Suite.SuiteClasses annotation.
 *
 * @author Jakub Szota
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResultTest.class,
        ScrapWorkerSetFactoryTest.class,
        ThePirateBayScrapWorkerTest.class,
        MvcConfigTest.class,
        SearchControllerTest.class,
        FileSizeConverterTest.class
})
public class AllTestsSuite {
}
