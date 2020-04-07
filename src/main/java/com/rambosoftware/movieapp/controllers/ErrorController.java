package com.rambosoftware.movieapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/error"})
public class ErrorController {

    @GetMapping({"error"})
   public String error (@RequestParam(value="error",required=false) String name, Model model){
        model.addAttribute("error", name);
        return "errors/notFound";
    }
}
