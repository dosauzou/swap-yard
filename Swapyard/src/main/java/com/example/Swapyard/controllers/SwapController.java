package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Swap;
import com.example.Swapyard.models.UserMatches;
import com.example.Swapyard.models.Users;
import com.example.Swapyard.repositories.MatchRepository;
import com.example.Swapyard.repositories.SwapRepository;
import com.example.Swapyard.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/swap/update")
public class SwapController {

    @Autowired
    SwapRepository swapRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/{userId}")
    public Object findByMatch(@RequestBody   String match, @PathVariable("userId") String userId) throws JsonProcessingException, ParseException, JSONException {
JSONObject j = new JSONObject(match);
System.out.println(j.get("swap"));
////            String jsons = match.get("swap").toString();
//        System.out.println(jsons);
////
            System.out.println(match);
        System.out.println(userId);
        Swap swap = new Swap();
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
JSONObject x = (JSONObject) j.get("swap");
        Swap.SwapDetails details = objectMapper.readValue(x.get("details").toString(), Swap.SwapDetails.class);
        swap.setDetails(details);
        swap.setStatus(true);
       Users user = userRepository.findByUsername(userId);
       Users user2 = userRepository.findByUsername((j.get("user").toString()));
       List<UserMatches> xo =  matchRepository.findAllByChatId(user2.getUsername()+""+user.getUsername());
       for(UserMatches m : xo){
           m.setSwap(swap);
           matchRepository.save(m);
       }

//        System.out.println(jsons);
//        System.out.println(json);
        //find match in database with user id where match is equal to whatever
        //parse that to database
//        JSONParser parser = new JSONParser();
//        JSONObject js = (JSONObject) parser.parse(json);
//        System.out.println(js.toJSONString());

//        Read more: https://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html#ixzz7QSYxLcfp
        return match;

    }

}
