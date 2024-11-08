package com.gai_app.gai_cars.service.kafka;

import com.gai_app.gai_cars.model.CarModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void getModelCreateMessageAndSend(CarModel carModel, String cause) {
        String message = "Car with id " + carModel.getId() + " has been "
                + cause + ". Car: " + carModel;
        sendNotification(message);
    }

    public void sendNotification(String message) {
        kafkaTemplate.send("notification-topic", message);
    }
}