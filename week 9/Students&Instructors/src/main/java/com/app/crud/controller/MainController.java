package com.app.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MainController handles requests related to the main page.
 */
@Controller
public class MainController {

    /**
     * Displays the index page.
     *
     * @return view of the index page
     */
    @GetMapping("/")
    public String index() {
        return "main/index";
    }

}
