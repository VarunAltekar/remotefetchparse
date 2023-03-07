package com.example.demo.service;

import com.example.demo.constant.UrlConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CountryCodesService {
    public String getCountryCodes(String country, String phoneNumber){
        String url = UrlConstant.url + country;

        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.
                getForEntity(url, String.class);
        log.info(response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root;
        try {
            root = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ArrayNode data = (ArrayNode) root.get("data");

        if("".equals(data.get(0).get("name").toString()))
            return "-1";

        List<String> callingCodes;
        try {
            callingCodes = objectMapper.readerForListOf(String.class).readValue(data.get(0).get("callingCodes"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return callingCodes.stream().sorted().collect(Collectors.toList()).get(0) + " " + phoneNumber;

    }
}
