package pl.szotaa;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Running this suite executes all tests categorised as UnitTest.
 *
 * @author Jakub Szota
 */

@RunWith(Categories.class)
@Categories.IncludeCategory(UnitTest.class)
@Suite.SuiteClasses({AllTestsSuite.class})
public class UnitTestsSuite {
}
