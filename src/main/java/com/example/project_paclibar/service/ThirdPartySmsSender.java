package com.example.project_paclibar.service;

public class ThirdPartySmsSender {
    public void sendText(String mobileNumber, String alertMessage) {
        System.out.println("[SMS Gateway] Sending text to " + mobileNumber + ": " + alertMessage);
    }
}