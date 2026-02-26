package com.example.Model.orders;


public class OrderEmailRequest {

    private Long orderId;

    // No-Args Constructor
    public OrderEmailRequest() {
    }

    // All-Args Constructor
    public OrderEmailRequest( Long orderId) {
     
        this.orderId = orderId;
    }


    public Long getOrderId() {
        return orderId;
    }



    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}