package pl.szotaa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.szotaa.torrentr.domain.ResultTest;

/**
 * Running this suite executes all tests. Every new test should be listed here in @Suite.SuiteClasses annotation.
 *
 * @author Jakub Szota
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResultTest.class
})
public class AllTestsSuite {
}
