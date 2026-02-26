
package com.example.Model.notification;

public class TestNotification {

    private String targetId;
    private String targetType; // user / vendor
    private String title;
    private String body;

    // ---------- GETTERS ----------

    public String getTargetId() {
        return targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    // ---------- SETTERS ----------

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
