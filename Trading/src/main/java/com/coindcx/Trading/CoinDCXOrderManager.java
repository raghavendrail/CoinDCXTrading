package com.coindcx.Trading;

import org.json.JSONObject;

public class CoinDCXOrderManager {

    private CoinDCXRestAPI restAPI;

    public CoinDCXOrderManager(CoinDCXRestAPI restAPI) {
        this.restAPI = restAPI;
    }

    public JSONObject prepareBuyOrderPayload(String market, double triggerPrice, double quantity) {
        JSONObject payload = new JSONObject();
        payload.put("side", "buy");
        payload.put("market", market);
        payload.put("price", triggerPrice);
        payload.put("quantity", quantity);
        return payload;
    }


    public JSONObject prepareSellOrderPayload(String market, double triggerPrice, double quantity) {
        JSONObject payload = new JSONObject();
        payload.put("side", "sell");
        payload.put("market", market);
        payload.put("price", triggerPrice);
        payload.put("quantity", quantity);
        return payload;
    }


    public JSONObject prepareCancelOrderPayload(String orderId) {
        JSONObject payload = new JSONObject();
        payload.put("order_id", orderId);
        return payload;
    }

  
    public void placeBuyOrder(String market, double triggerPrice, double quantity) {
        JSONObject payload = prepareBuyOrderPayload(market, triggerPrice, quantity);
        
    }

    public void placeSellOrder(String market, double triggerPrice, double quantity) {
        JSONObject payload = prepareSellOrderPayload(market, triggerPrice, quantity);
       
    }

   
    public void cancelOrder(String orderId) {
        JSONObject payload = prepareCancelOrderPayload(orderId);
        
    }
}
