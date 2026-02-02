package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.service.impl;

import com.teamgold.goldenharvestcustomsupport.common.exception.BusinessException;
import com.teamgold.goldenharvestcustomsupport.common.exception.ErrorCode;
import com.teamgold.goldenharvestcustomsupport.common.infra.file.service.FileUploadService;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.comment.CommentCreateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.inquiry.InquiryCreateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.dto.request.inquiry.InquiryUpdateRequest;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.service.InquiryService;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.inquiry.Inquiry;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.inquiry.ProcessingStatus;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.infrastructure.repository.inquiry.InquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final FileUploadService fileUploadService;

    @Override
    @Transactional
    public void create(
            String userId,
            InquiryCreateRequest request,
            MultipartFile file
    ) throws IOException {

        String fileUrl = null;

        if (file != null && !file.isEmpty()) {
            var savedFile = fileUploadService.upload(file);
            fileUrl = savedFile.getFileUrl();
        }

        String inquiryId = UUID.randomUUID().toString();

        Inquiry inquiry = Inquiry.builder()
                .inquiryId(inquiryId)
                .userId(userId)
                .salesOrderId(request.salesOrderId())
                .title(request.title())
                .body(request.body())
                .fileUrl(fileUrl)
                .processingStatus(ProcessingStatus.PENDING)
                .build();

        inquiryRepository.save(inquiry);

    }

    @Override
    @Transactional
    public void delete(String userId, String inquiryNo) {
        Inquiry inquiry = inquiryRepository.findByInquiryIdAndUserId(inquiryNo, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INQUIRY_NOT_FOUND));

        inquiryRepository.delete(inquiry);


    }

    @Override
    @Transactional
    public void update(
            String userId,
            String inquiryNo,
            InquiryUpdateRequest request,
            MultipartFile file
    ) throws IOException {
        Inquiry inquiry = inquiryRepository
                .findByInquiryIdAndUserId(inquiryNo, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INQUIRY_NOT_FOUND));

        inquiry.updatedInquiry(request.title(), request.body());

        if (file != null && !file.isEmpty()) {
            var savedFile = fileUploadService.upload(file);
            inquiry.updateFile(savedFile.getFileUrl());
        }
    }

    @Override
    @Transactional
    public void comment(String inquiryNo, CommentCreateRequest request) {
        Inquiry inquiry = inquiryRepository.findById(inquiryNo)
                .orElseThrow(() -> new BusinessException(ErrorCode.INQUIRY_NOT_FOUND));

        if (request.comment() != null) {
            inquiry.updatedComment(request.comment()); // 답변 작성
            inquiry.updatedProcessingStatus(request.status()); // 문의 상태 변경
        }


    }


}
