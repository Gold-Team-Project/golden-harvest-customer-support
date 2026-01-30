package com.teamgold.goldenharvestcustomsupport.customersupport.command.infrastructure.repository.inquiry;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, String> {
    Optional<Inquiry> findByInquiryIdAndUserId(String inquiryId, String userId);
}
