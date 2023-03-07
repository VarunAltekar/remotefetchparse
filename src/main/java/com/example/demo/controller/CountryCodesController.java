package com.example.demo.controller;

import com.example.demo.service.CountryCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryCodesController {

@Autowired
private CountryCodesService countryCodesService;
    @GetMapping("/countryCodes")
    public String countryCodes(@RequestParam String country, @RequestParam String phoneNumber){
        return countryCodesService.getCountryCodes(country, phoneNumber);
    }
}
