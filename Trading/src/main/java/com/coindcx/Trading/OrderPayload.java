package com.coindcx.Trading;

import org.json.JSONObject;

public class OrderPayload {
    private String orderType; // "buy" or "sell"
    private String market; // e.g., "ETNUSDT"
    private double price;
    private double quantity;

    public OrderPayload(String orderType, String market, double price, double quantity) {
        this.orderType = orderType;
        this.market = market;
        this.price = price;
        this.quantity = quantity;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("order_type", this.orderType);
        json.put("market", this.market);
        json.put("price", this.price);
        json.put("quantity", this.quantity);
        return json;
    }
}
