package com.example.Swapyard.controllers;

import com.example.Swapyard.models.Items;
import com.example.Swapyard.models.Swap;
import com.example.Swapyard.models.UserMatches;
import com.example.Swapyard.models.Users;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.MatchRepository;
import com.example.Swapyard.repositories.SwapRepository;
import com.example.Swapyard.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/swap/update")
public class SwapController {

    @Autowired
    SwapRepository swapRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/list/{userId}")
    public Object addToSwapList(@RequestBody String match, @PathVariable("userId") String userId) throws JsonProcessingException, ParseException, JSONException {
        JSONObject j = new JSONObject(match);

        System.out.println(match);
        Swap swap = new Swap();

//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject x = new JSONObject(match);
        String chatId = x.getString("chatId");
        JSONArray jsonArray = x.getJSONArray("items");
        Set<Items> itemList = new HashSet<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            itemList.add(itemRepository.getById(jsonArray.getLong(i)));
        }
//        if(swap.getSwapItems()!=null){
//            swap.getSwapItems().addAll(itemList);
//        }else{
//            List<Items> swapList = new ArrayList<>();
//            swapList.addAll(itemList);
//            swap.setSwapItems(swapList);
//        }

        List<UserMatches> xo = new ArrayList();
        xo.addAll(matchRepository.findByChatIdOrderByIdDesc(chatId));
        Swap s = new Swap();
        s.setSwapItems(itemList);

        for (int i = 0; i < 2; i++) {
            if (xo.get(i).getSwap() == null) {
                xo.get(i).setSwap(s);
                matchRepository.save(xo.get(i));

            } else {

                xo.get(i).getSwap().getSwapItems().addAll(itemList);
                matchRepository.save(xo.get(i));

            }
        }
        return match;

    }

    @RequestMapping("/{userId}")
    public Object findByMatch(@RequestBody String match, @PathVariable("userId") String userId) throws JsonProcessingException, ParseException, JSONException {
        JSONObject j = new JSONObject(match);
        ObjectMapper objectMapper = new ObjectMapper();
        String chatId = j.getString("chatId");

//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject x = (JSONObject) j.get("swap");
        Swap.SwapDetails details = objectMapper.readValue(x.get("details").toString(), Swap.SwapDetails.class);
//        swap.setSwapItems();

        List<UserMatches> xo = new ArrayList();
        xo.addAll(matchRepository.findByChatIdOrderByIdDesc(chatId));

        for (int i = 0; i < 2; i++) {

            xo.get(i).getSwap().setDetails(details);
            xo.get(i).getSwap().setSwapStatus("false");
            matchRepository.save(xo.get(i));


        }

        return match;

    }

}
