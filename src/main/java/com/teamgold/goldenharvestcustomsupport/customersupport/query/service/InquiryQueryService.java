package com.teamgold.goldenharvestcustomsupport.customersupport.query.service;

import com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response.AdminInquiryDetailResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response.AdminInquiryListResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response.InquiryDetailResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.query.dto.response.InquiryListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryQueryService {
    //문의 내역
    List<InquiryListResponse> getAllInquiry(String userId, Integer page, Integer size);
    //문의 상세
    InquiryDetailResponse getDetailInquiry(String inquiryNo);
    //문의 내역(관리자)
    List<AdminInquiryListResponse> getAllAdminInquiry(Integer page, Integer size);
    //문의 상세(관리자)
    AdminInquiryDetailResponse getDetailAdminInquiry(String inquiryNo);
}
