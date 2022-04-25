package com.example.Swapyard.services;

import com.example.Swapyard.models.*;
import com.example.Swapyard.repositories.ItemRepository;
import com.example.Swapyard.repositories.MatchRepository;
import com.example.Swapyard.repositories.SwipeRepository;
import com.example.Swapyard.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SwipeService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SwipeRepository swipeRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Set<Users> c(List<Users> a, List<Users> b) {
        //will return a list of any users that intersect

        Set<Users> result = a.stream()
                .distinct()
                .filter(b::contains)
                .collect(Collectors.toSet());
        return result;
    }

    public Set<Users> comparison(Users b) throws IOException {
        Users u = userRepository.getById(b.getId());
        List<UserMatches> allMatches = new ArrayList<>();
        //List of swipes by the user, this way we can see the userId of those items
        List<Swipes> swipesMade = swipeRepository.findByUserId(u.getId());
        List<Users> identifySwipeMade = swipesMade.stream().map(Swipes::getItems).collect(Collectors.toList()).stream().map((Items::getUsers)).collect(Collectors.toList());
        //swipes to the user
        List<Items> itemsList = u.getItems();
        List<Swipes> swipesReceieved = new ArrayList<>();


        for (Items i : itemsList) {
            if (i.getSwipes() != null)
                swipesReceieved.addAll(i.getSwipes());
        }
        List<Users> identifySwipeRecieved = userRepository.findAllById(swipesReceieved.stream().map(Swipes::getUserId).collect(Collectors.toList()));
        Set<Users> result = c(identifySwipeMade, identifySwipeRecieved);

        //Will be refined
        for (Users x : identifySwipeMade) {
            if (x.getUsername().equals(b)) {
                identifySwipeMade.remove(x);
            }

        }
        for (Users g : identifySwipeRecieved) {
            if (g.getUsername().equals(b)) {
                identifySwipeRecieved.remove(g);
            }
        }


        for (Users d : result) {
            UserMatches userMatches = new UserMatches();

            if (!u.getMatches().stream().anyMatch(o -> o.getMatch().getUsername().equals(d.getUsername()))) {
                userMatches.setMatch(d);
                u.getMatches().add(userMatches);
                userRepository.save(u);
            }
        }

        for (Users un : result) {
            Users r = userRepository.findByUsername(un.getUsername());
            if (!r.getMatches().stream().anyMatch(o -> o.getMatch().getUsername().equals(u.getUsername()))) {
                UserMatches wow = new UserMatches();
                wow.setMatch(u);
                r.getMatches().add(wow);
                System.out.println(r.getMatches());
                userRepository.save(r);
            }

        }

        //now what to do with the result?
//        for(Users o: result){
//            Users user = userRepository.findByUsername(o.getUsername());
//            Subscriptions s = user.getSubs();
//            String firstjson = new ObjectMapper().writeValueAsString(s);
//           String json = firstjson.replace("key","keys");
//            System.out.println(json);
////            push notification to the user
//            URL url = new URL("http://localhost:3000/notification");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("Accept", "application/json");
//            con.setDoOutput(true);
//            con.setDoInput (true);
//            try(OutputStream os = con.getOutputStream()) {
//                byte[] input = json.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
//            InputStream responseStream = con.getInputStream();
//            int responseCode1 = con.getResponseCode();
//            System.out.println(responseCode1);
//        }

        //'ive
        //I want to return, the item matched to a user
        return result;
    }
}






