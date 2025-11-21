package az.company.usermanagement.kafka.producer;

import az.company.usermanagement.kafka.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "user-events";

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}