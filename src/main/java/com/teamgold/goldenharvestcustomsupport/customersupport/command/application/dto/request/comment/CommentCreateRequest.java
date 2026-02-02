package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.comment;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.inquiry.ProcessingStatus;

public record CommentCreateRequest (
        String comment,
        ProcessingStatus status
){}
