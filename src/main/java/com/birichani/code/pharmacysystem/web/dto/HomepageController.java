package com.birichani.code.pharmacysystem.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
    @RequestMapping("/homepage")
    public String homePage() {

        return "home-page";
    }
}
