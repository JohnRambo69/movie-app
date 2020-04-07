package com.rambosoftware.movieapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/home"})
    public String handler() {

        return "index";
    }
}
