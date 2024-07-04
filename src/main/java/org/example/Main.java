package org.example;

import java.util.Scanner;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
    public static void main(String[] args) {
        String fullURL = "http://api.weatherapi.com/v1/current.json?key=";
        final String param = "&q=";

        Scanner scan = new Scanner(System.in);
        OkHttpClient client = new OkHttpClient();

        System.out.println("Please enter your API key: ");
        String key = scan.nextLine();
        fullURL += key;
        fullURL += param;

        System.out.println("Please enter a city or zipcode: ");
        String area = scan.nextLine();
        System.out.println();
        fullURL += area;

        Request request = new Request.Builder()
                .url(fullURL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();
            int startIndex = data.indexOf("temp_f");
            String temp = data.substring(startIndex + 8, startIndex + 12);

            System.out.println("Temperature in past last hour (in Fahrenheit): " + temp + " degrees, for: " + area);
        }
        catch (IOException ex) {
            System.out.println("Error on request: " + ex.getLocalizedMessage());
        }

    }
}