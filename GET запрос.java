package com.company;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://robocodeapi.herokuapp.com?";
        Map<String, String> map = new HashMap<>();
        map.put("authToken", "qwertyuiop");
        map.put("part", "school");
        for(Map.Entry entry : map.entrySet()){
            url = url + entry.getKey() + "=" + entry.getValue() + "&";
        }
        URL obj = new URL(url);
        HttpURLConnection connect = (HttpURLConnection) obj.openConnection();
        connect.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String response = in.readLine();
        in.close();
        FileWriter wr = new FileWriter("robocode.json");
        wr.write(response);
        wr.close();
        System.out.println(obj);
    }
}
