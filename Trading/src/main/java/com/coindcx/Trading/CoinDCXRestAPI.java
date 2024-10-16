package com.coindcx.Trading;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinDCXRestAPI {

    private static final String BASE_URL = "https://api.coindcx.com";
    private static final String MARKETS_DETAILS_URL = BASE_URL + "/exchange/v1/markets_details";
    private static final String PLACE_ORDER_URL = BASE_URL + "/exchange/v1/orders/create"; // Place order endpoint
    private static final String CANCEL_ORDER_URL = BASE_URL + "/exchange/v1/orders/cancel"; // Cancel order endpoint
    private static final String API_KEY = "a13eb9093a33a2c50e72ff82e5e7cc41da9454d211c83f22"; // Your API key

    public static void main(String[] args) {
        fetchMarketDetails();
        // You can call placeOrder and cancelOrder methods here to test them
        // placeOrder("ETNUSDT", "buy", 100, 0.000001); // Example order
        // cancelOrder("ORDER_ID"); // Replace ORDER_ID with your actual order ID
    }

    public static void fetchMarketDetails() {
        try {
            URL url = new URL(MARKETS_DETAILS_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Market Details: " + response.toString());
            } else {
                System.out.println("Failed to fetch market details. HTTP Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void placeOrder(String market, String side, double quantity, double price) {
        try {
            URL url = new URL(PLACE_ORDER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON payload for placing an order
            String jsonInputString = String.format("{\"market\":\"%s\",\"side\":\"%s\",\"quantity\":%.2f,\"price\":%.6f}", market, side, quantity, price);

            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code (Order): " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Order Response: " + response.toString());
            } else {
                System.out.println("Failed to place order. HTTP Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelOrder(String orderId) {
        try {
            URL url = new URL(CANCEL_ORDER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            
            String jsonInputString = String.format("{\"order_id\":\"%s\"}", orderId);

            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code (Cancel Order): " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Cancel Order Response: " + response.toString());
            } else {
                System.out.println("Failed to cancel order. HTTP Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
