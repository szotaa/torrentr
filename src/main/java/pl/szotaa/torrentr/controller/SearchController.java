package pl.szotaa.torrentr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.szotaa.torrentr.domain.Result;
import pl.szotaa.torrentr.service.SearchService;

import java.util.List;

/**
 * Controller for /search requests.
 *
 * @author Jakub Szota
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    /**
     * Search service providing search functionality.
     */

    private final SearchService searchService;

    /**
     * Processes search request.
     *
     * @param searchQuery Search query entered by user.
     * @return Model object named 'results' containing search results scraped from other torrent search engines.
     *          Resolves to 'searchResult' view.
     */

    @GetMapping("/{searchQuery}")
    public ModelAndView processSearchQuery(@PathVariable String searchQuery){
        List<Result> results = searchService.search(searchQuery);
        return new ModelAndView("searchResults","results", results);
    }
}
