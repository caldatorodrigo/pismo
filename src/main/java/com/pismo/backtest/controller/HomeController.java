package com.pismo.backtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Home")
@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        return "I'm alive";
    }

}
