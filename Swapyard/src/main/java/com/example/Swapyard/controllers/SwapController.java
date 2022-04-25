package com.example.Swapyard.controllers;

import com.example.Swapyard.repositories.MatchRepository;
import com.example.Swapyard.repositories.SwapRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/swap/update")
public class SwapController {

    @Autowired
    SwapRepository swapRepository;
    @Autowired
    MatchRepository matchRepository;

    @RequestMapping("/{userId}")
    public Object findByMatch(@RequestBody Object match, @PathVariable("userId") String userId) throws JsonProcessingException, ParseException {
        System.out.println(userId);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(match);
//        System.out.println(json);
        //find match in database with user id where match is equal to whatever
        //parse that to database
        JSONParser parser = new JSONParser();
        JSONObject js = (JSONObject) parser.parse(json);
        System.out.println(js.toJSONString());

//        Read more: https://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html#ixzz7QSYxLcfp
        return match;

    }

}
