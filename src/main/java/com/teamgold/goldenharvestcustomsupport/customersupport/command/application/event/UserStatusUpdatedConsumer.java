package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event.dto.UserStatusUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserStatusUpdatedConsumer {

    private final ApplicationEventPublisher publisher;

    @KafkaListener(topics = "user.status.updated", groupId = "golden.harvest.custom.support.processor")
    void consume(UserStatusUpdatedEvent event) {
        log.info("UserStatusUpdateEvent consuming");

        publisher.publishEvent(event);
    }
}
