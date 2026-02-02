package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event.dto.InquiryRequestAcceptedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InquiryRequestAcceptedEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void inquiryRequestAcceptedEvent(InquiryRequestAcceptedEvent event) {
        log.info("event sourcing");
        publisher.publishEvent(event);
    }
}
