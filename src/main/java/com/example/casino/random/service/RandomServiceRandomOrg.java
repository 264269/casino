package com.example.casino.random.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class RandomServiceRandomOrg implements RandomService {
    @Override
    public int getOneRandomInt(int min, int max) {
        Integer result;
        try {
            RestClient restClient = RestClient.create();
            String uriTemplate = "https://www.random.org/integers/" +
                    "?num=%s" +
                    "&min=%s" +
                    "&max=%s" +
                    "&col=%s" +
                    "&base=%s" +
                    "&format=%s" +
                    "&rnd=%s";
            String uriResult = String.format(uriTemplate, 1, min, max, 1, 10, "plain", "new");
            URI uri = new URI(uriResult);
            String requestResult =restClient.get().uri(uri).retrieve().body(String.class).trim();
            result = Integer.parseInt(requestResult);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            result = min + (int) (Math.random() * (max - min));
        }
        return result;
    }
}
