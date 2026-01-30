package com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response;

public record FileDownloadResponse (
    Long fileId,
    String fileName,
    String downloadUrl,
    String contentType
){}
