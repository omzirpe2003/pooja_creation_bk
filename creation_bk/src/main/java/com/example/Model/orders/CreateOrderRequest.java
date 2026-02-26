package com.example.Model.orders;

import java.util.List;


public class CreateOrderRequest {

    private List<OrderItemRequest> items;

    // No-Args Constructor
    public CreateOrderRequest() {
    }

    // All-Args Constructor
    public CreateOrderRequest(List<OrderItemRequest> items) {
        this.items = items;
    }

    // Getter
    public List<OrderItemRequest> getItems() {
        return items;
    }

    // Setter
    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
