package pl.szotaa.torrentr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.szotaa.IntegrationTest;
import pl.szotaa.torrentr.service.SearchService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@Category(IntegrationTest.class)
public class SearchControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new SearchController(searchService))
                .build();
    }

    @Test
    public void requestSearch_correctViewResolved() throws Exception{
        //given
        String searchQuery = "searchQuery";

        //when&then
        mockMvc.perform(get("/search/" + searchQuery))
                .andExpect(status().isOk())
                .andExpect(view().name("searchResults"))
                .andExpect(model().attributeExists("results"));

        Mockito.verify(searchService, Mockito.times(1)).search(Mockito.anyString());
    }
}
