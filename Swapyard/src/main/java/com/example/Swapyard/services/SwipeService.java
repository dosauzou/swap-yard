package com.example.Swapyard.services;

import com.example.Swapyard.models.*;
import com.example.Swapyard.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    @Autowired
    private SwapRepository swapRepository;


    private String chatId;
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
        List<Items> swappedItems = new ArrayList<>();
        List<Swap> swaps =  swapRepository.findAll();
        swaps.forEach(p-> swappedItems.addAll(p.getSwapItems()));
        HashSet<Items> items = new HashSet<>();
        HashSet<Items> items2 = new HashSet<>();

        items.addAll(swipesMade.stream().map(Swipes::getItems).collect(Collectors.toList()));
        items.remove(swappedItems);

        List<Users> identifySwipeMade = items.stream().map((Items::getUsers)).collect(Collectors.toList());
        //swipes to the user
        List<Items> itemsList = u.getItems();
        List<Swipes> swipesReceieved = new ArrayList<>();


        for (Items i : itemsList) {
            if (i.getSwipes() != null)
                swipesReceieved.addAll(i.getSwipes());
        }
        swipesReceieved.removeIf(p-> swappedItems.contains(p.getItems()));
//       items2.addAll(swipesReceieved.stream().map(Swipes::getItems).collect(Collectors.toList()));
//        items2.removeAll(swappedItems);

        List<Users> identifySwipeRecieved = userRepository.findAllById(swipesReceieved.stream().map(Swipes::getUserId).collect(Collectors.toList()));
        Set<Users> result = c(identifySwipeMade, identifySwipeRecieved);


        for (Users d : result) {
            UserMatches userMatches = new UserMatches();

            if (!u.getMatches().stream().anyMatch(o -> o.getMatch().getUsername().equals(d.getUsername()) && (o.getSwap()==null || o.getSwap().getSwapStatus()==null ||o.getSwap().getSwapStatus()=="false"))){
                System.out.println("This is a success");

                userMatches.setMatch(d);
                userMatches.setChatId(d.getUsername()+""+b.getUsername());
                u.getMatches().add(userMatches);
//                sendNotification(u);
                //Get users current subscription and send a user a notification of the new match
                userRepository.save(u);
            }
        }

        for (Users un : result) {
            Users r = userRepository.findByUsername(un.getUsername());

            if (!r.getMatches().stream().anyMatch(o -> o.getMatch().getUsername().equals(u.getUsername()) && (o.getSwap()==null || o.getSwap().getSwapStatus()==null ||o.getSwap().getSwapStatus()=="false"))) {
                System.out.println("This is a new match" + un.getUsername() + result.size());
                UserMatches wow = new UserMatches();
                wow.setMatch(u);
                wow.setChatId(r.getUsername()+b.getUsername());
                r.getMatches().add(wow);
                sendNotification(r);
                System.out.println(r.getMatches());
                //Get users current subscription and send a user a notification of the new match
                userRepository.save(r);
            }

            System.out.println(result);

        }

        return result;
    }

    private void sendNotification(Users user) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        for (Users o : result) {
//            Users user = userRepository.findByUsername(x.getUsername());
//            Subscriptions s = user.getSubs();
            String firstjson = new ObjectMapper().writeValueAsString(user.getSubs());
            String json = firstjson.replace("key", "keys");
//            System.out.println(json);
//            push notification to the user


            URL url = new URL("http://localhost:3000/notification");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            System.out.println(json);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode1 = con.getResponseCode();
            System.out.println(responseCode1);

    }
}






