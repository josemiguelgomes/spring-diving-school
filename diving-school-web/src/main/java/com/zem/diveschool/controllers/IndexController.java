package com.zem.diveschool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zem on 20/04/2022.
 */
@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "index.html"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/oups")
    public String oupsHandler(){
        return "notimplemented";
    }
}
