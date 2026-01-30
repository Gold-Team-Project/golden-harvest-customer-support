package com.teamgold.goldenharvestcustomsupport.customersupport.command.infrastructure.repository.snapshot;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.snapshot.InquiryWriterSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryWriterSnapshotRepository extends JpaRepository<InquiryWriterSnapshot, String> {
}
