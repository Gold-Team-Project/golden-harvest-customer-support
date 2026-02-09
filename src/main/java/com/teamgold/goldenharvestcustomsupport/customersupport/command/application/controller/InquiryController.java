package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.controller;

import com.teamgold.goldenharvestcustomsupport.common.response.ApiResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.comment.CommentCreateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.inquiry.InquiryCreateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.inquiry.InquiryUpdateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @PostMapping(value = "/inquiries", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> save(
            @AuthenticationPrincipal Jwt jwt,
            @RequestPart("request") InquiryCreateRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        inquiryService.create(jwt.getSubject(), request, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(null));
    }

    @DeleteMapping("/inquiries/{inquiryId}")
    public ResponseEntity<ApiResponse<?>> delete(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String inquiryId) {

        inquiryService.delete(jwt.getSubject(), inquiryId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping(value = "/inquiries/{inquiryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String inquiryId,
            @RequestPart("request") InquiryUpdateRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        inquiryService.update(jwt.getSubject(), inquiryId, request, file);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/admin/inquiries/{inquiryId}/process")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> process(
            @PathVariable String inquiryId,
            @RequestBody CommentCreateRequest request) {
        inquiryService.comment(inquiryId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
