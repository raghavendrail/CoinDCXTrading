package com.coindcx.Trading;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class CoinDCXWebSocketClient extends WebSocketClient {

    public CoinDCXWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket connection established.");

       
        String subscribeMessage = "{\"op\": \"subscribe\", \"args\": [\"ETN_USDT\"]}";
        send(subscribeMessage);
        System.out.println("Subscribed to ETN_USDT.");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket connection closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    public static void main(String[] args) {
        try {
            URI uri = new URI("wss://stream.coindcx.com");
            CoinDCXWebSocketClient client = new CoinDCXWebSocketClient(uri);
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
