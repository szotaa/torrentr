package pl.szotaa.torrentr.config;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szotaa.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@RunWith(SpringRunner.class)
@Category(IntegrationTest.class)
@ContextConfiguration(classes = {MvcConfig.class})
public class MvcConfigTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String CSS_PATH = "/static/css/styles.css";
    private static final String JS_PATH = "/static/js/scripts.js";

    @Test
    public void requestJsResource_resourceGetsServed() throws Exception {
        mockMvc.perform(get(JS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/javascript"));
    }

    @Test
    public void requestCssResource_resourceGetsServed() throws Exception {
        mockMvc.perform(get(CSS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/css"));
    }

    @Test
    public void requestIndex_indexGetsServed() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}