package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event.dto;

import lombok.Builder;

@Builder
public record InquiryRequestAcceptedEvent(
    String userEmail,
    String salesOrderId,
    String processingStatus
) { }
