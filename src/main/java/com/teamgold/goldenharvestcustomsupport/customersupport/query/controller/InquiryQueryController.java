package com.teamgold.goldenharvestcustomsupport.customersupport.query.controller;

import com.teamgold.goldenharvestcustomsupport.common.response.ApiResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.query.service.InquiryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InquiryQueryController {
    private final InquiryQueryService inquiryQueryService;

    @GetMapping("/inquiries")
    public ResponseEntity<ApiResponse<?>> getAllInquiry(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(ApiResponse.success(inquiryQueryService.getAllInquiry(userId, page, size)));
    }

    @GetMapping("/inquiries/{inquiryNo}")
    public ResponseEntity<ApiResponse<?>> getDetailInquiry(@PathVariable String inquiryNo) {
        return ResponseEntity.ok(ApiResponse.success(inquiryQueryService.getDetailInquiry(inquiryNo)));
    }

    @GetMapping("/admin/inquiries")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getAllAdminInquiry(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(inquiryQueryService.getAllAdminInquiry(page, size)));
    }

    @GetMapping("/admin/inquiries/{inquiryNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getDetailAdminInquiry(@PathVariable String inquiryNo) {
        return ResponseEntity.ok(ApiResponse.success(inquiryQueryService.getDetailAdminInquiry(inquiryNo)));
    }

}
