package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.inquiry;

public record InquiryCreateRequest(
        String salesOrderId,
        String title,
        String body
) {
}
