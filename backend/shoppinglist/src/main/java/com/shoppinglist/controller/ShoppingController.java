package com.shoppinglist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {

    @RequestMapping( "/X" )
    public String echo() {
        return "Halllo ich bin XXXX";
    }

}
