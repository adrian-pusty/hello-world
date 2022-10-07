package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}

@Controller
class ModelAttributeExample
{
    @GetMapping(value = "/v1", produces = "application/json")
    public @ResponseBody String getVal1(@ModelAttribute("val1") String str1)
    {
        return str1;
    }

    @GetMapping(value = "/v2", produces = "application/json")
    public @ResponseBody String getVal2(@ModelAttribute("val2") String str2)
    {
        return str2;
    }

    @ModelAttribute
    public void foo1(Model model)
    {
        model.addAttribute("val1", "Hello World");
    }

    // @ModelAttribute // commented out -> blank page for http://localhost:8080/v2
    public void foo2(Model model)
    {
        model.addAttribute("val2", "Bye World");
    }
}