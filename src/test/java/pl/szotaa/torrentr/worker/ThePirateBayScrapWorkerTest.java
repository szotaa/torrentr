package pl.szotaa.torrentr.worker;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.szotaa.UnitTest;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.worker.webclient.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Category(UnitTest.class)
public class ThePirateBayScrapWorkerTest {

    private String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\"/>\n" +
            "    <title>TPBexample</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <table>\n" +
            "        <thead>\n" +
            "\n" +
            "        </thead>\n" +
            "        <tbody>\n" +
            "            <tr>\n" +
            "                <td>\n" +
            "                    <a class=\"detLink\" href=\"infoLink\">Title</a>\n" +
            "                    <a href=\"wrongMagnetLink\">wrongMagnetLink</a>\n" +
            "                    <a href=\"wrongMagnetLink\">wrongMagnetLink</a>\n" +
            "                    <a href=\"magnetLink\">correctMagnetLink</a>\n" +
            "                    <font>Uploaded 01-01 2001, Size 100.0 MiB, ULed by test\t</font>" +
            "                </td>\n" +
            "                <td>\n" +
            "                    empty\n" +
            "                </td>\n" +
            "                <td class=\"seeds\">\n" +
            "                    100\n" +
            "                </td>\n" +
            "                <td class=\"peers\">\n" +
            "                    100\n" +
            "                </td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "</body>\n" +
            "</html>";

    @Test
    public void scrap_correctDataGetsScraped() throws Exception {
        //given
        Document document = Jsoup.parse(html);
        Connection connection = mock(Connection.class);
        WebClient webClient = mock(WebClient.class);
        when(webClient.connect(anyString())).thenReturn(connection);
        when(connection.get()).thenReturn(document);
        AbstractScrapWorker scrapWorker = new ThePirateBayScrapWorker("searchQuery", webClient);

        //when
        Set<Result> resultSet = scrapWorker.scrap();
        List<Result> resultList = new ArrayList<>(resultSet);

        //then
        assertEquals(1, resultList.size());
        Result result = resultList.get(0);
        assertEquals("Title", result.getTitle());
        assertEquals("magnetLink", result.getMagnetLink());
        assertEquals("https://thepiratebay.org/infoLink", result.getInfoLink());
        assertEquals(100, result.getSeeds());
        assertEquals(100, result.getPeers());
        assertEquals("https://thepiratebay.org", result.getSource());
        assertEquals(102400, result.getSize(), 0.01);
    }
}