package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://robocodeapi.herokuapp.com/add_student?";
        Map<String, String> map = new HashMap<>();
        map.put("authToken", "qwertyuiop");
        for (Map.Entry entry : map.entrySet()){
            url = url + entry.getKey() + "=" + entry.getValue() + "&";
        }
        URL obj = new URL(url);
        HttpURLConnection connect = (HttpURLConnection) obj.openConnection();
        connect.setRequestMethod("POST");
        connect.setRequestProperty("Content-Type", "application/json");
        connect.setDoOutput(true);
        String body = "{\"name\": \"Sergey\", \"age\": 28}";
        DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
        wr.writeBytes(body);
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String response = in.readLine();
        in.close();
        System.out.println(response);

    }
}
