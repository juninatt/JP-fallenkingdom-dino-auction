package se.pbt.mrcoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling requests related to the home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    /**
     * Handles the GET request for the home page.
     *
     * @return the view for the home page
     */
    @GetMapping()
    public String homePage() {
        return "home.html";
    }
}
