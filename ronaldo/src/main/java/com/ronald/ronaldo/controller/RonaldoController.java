package com.ronald.ronaldo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RonaldoController {

    @GetMapping(path="/webhook/studentAdded/{name}")
    public @ResponseBody String studentAdded(@PathVariable String name) {
        // This returns a JSON or XML with the users
        System.out.println("Student name: " + name);
        return "webhook received";
    }



}

