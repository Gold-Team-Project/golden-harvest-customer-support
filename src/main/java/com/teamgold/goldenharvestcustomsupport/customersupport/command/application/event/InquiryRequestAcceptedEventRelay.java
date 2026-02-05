package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event;

import com.teamgold.goldenharvestcustomsupport.common.broker.KafkaProducerHelper;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event.dto.InquiryRequestAcceptedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InquiryRequestAcceptedEventRelay {
    private final KafkaProducerHelper producer;

    @Async
    @EventListener
    public void inquiryRequestAcceptedRelay(InquiryRequestAcceptedEvent event) {
        log.info("Publishing inquiry request accepted event to kafka");

        producer.send("inquiry.request.accepted",
                UUID.randomUUID().toString(),
                event,
                null);

        log.info("Successfully published event to kafka");
    }

}
