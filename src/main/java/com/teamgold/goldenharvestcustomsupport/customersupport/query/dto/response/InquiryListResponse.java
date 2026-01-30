package com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.inquiry.ProcessingStatus;
import lombok.Getter;

@Getter
public class InquiryListResponse {
    String InquiryNo; //문의번호
    String createdAt; //작성일
    String title; //제목
    ProcessingStatus processingStatus; //처리 상태

}
