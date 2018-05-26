package pl.szotaa.torrentr.domain;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.szotaa.UnitTest;

import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class ResultTest {

    @Test
    public void compareTo() {
        //given
        Result resultLessSeeds = Result.builder()
                .seeds(50)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result resultMoreSeeds = Result.builder()
                .seeds(500)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result resultEqualSeeds = Result.builder()
                .seeds(500)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        //when&then
        assertTrue(resultLessSeeds.compareTo(resultMoreSeeds) < 0);
        assertTrue(resultEqualSeeds.compareTo(resultMoreSeeds) == 0);
        assertTrue(resultMoreSeeds.compareTo(resultLessSeeds) > 0);
    }

    @Test
    public void equals(){
        //given
        Result result1 = Result.builder()
                .seeds(50)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result result2 = Result.builder()
                .seeds(50)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result result3 = Result.builder()
                .seeds(500)
                .peers(100)
                .size(999)
                .title("example result1")
                .magnetLink("magnet:?xt=abc:def:EXAMPLEMAGNETLINK")
                .infoLink("https://examplelink.com/")
                .build();

        Result result4 = Result.builder()
                .seeds(500)
                .peers(100)
                .size(999)
                .title("example result1")
                .magnetLink("magnet:?xt=abc:def:other")
                .infoLink("https://examplelink.com/")
                .build();

        //when&then
        assertTrue(result1.equals(result1));
        assertTrue(result1.equals(result2));
        assertTrue(result2.equals(result1));
        assertTrue(result1.equals(result3));
        assertTrue(!result1.equals(null));
        assertTrue(!result1.equals(result4));
    }

    @Test
    public void hashCode_equalObjectsHaveEqualHashCodes(){
        //given
        Result result1 = Result.builder()
                .seeds(50)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result result2 = Result.builder()
                .seeds(50)
                .peers(10)
                .size(10000)
                .title("example result")
                .magnetLink("magnet:?xt=abc:def:examplemagnetlink")
                .infoLink("https://examplelink.com/")
                .build();

        Result result3 = Result.builder()
                .seeds(500)
                .peers(100)
                .size(999)
                .title("example result1")
                .magnetLink("magnet:?xt=abc:def:EXAMPLEMAGNETLINK")
                .infoLink("https://examplelink.com/")
                .build();

        //when&then
        assertTrue(result1.hashCode() == result2.hashCode());
        assertTrue(result1.hashCode() == result3.hashCode());
    }
}