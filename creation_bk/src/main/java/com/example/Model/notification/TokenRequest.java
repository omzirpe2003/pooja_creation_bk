
package com.example.Model.notification;

public class TokenRequest {

    private String userId;
    private String token;
    private String type;    // user / vendor
    private String device;

    // ---------- GETTERS ----------

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getDevice() {
        return device;
    }

    // ---------- SETTERS ----------

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
