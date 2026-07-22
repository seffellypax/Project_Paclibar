package com.example.project_paclibar.service;

public class SmsAdapter implements NotificationService {
    private ThirdPartySmsSender smsSender;

    public SmsAdapter(ThirdPartySmsSender smsSender) {
        this.smsSender = smsSender;
    }

    @Override
    public void sendNotification(String recipient, String message) {
        smsSender.sendText(recipient, message);
    }
}