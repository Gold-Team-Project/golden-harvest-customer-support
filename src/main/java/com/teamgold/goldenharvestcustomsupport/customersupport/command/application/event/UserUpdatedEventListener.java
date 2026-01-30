package com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event;

import com.teamgold.goldenharvestcustomsupport.customersupport.command.application.event.dto.UserUpdatedEvent;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.domain.snapshot.InquiryWriterSnapshot;
import com.teamgold.goldenharvestcustomsupport.customersupport.command.infrastructure.repository.snapshot.InquiryWriterSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdatedEventListener {

	private final InquiryWriterSnapshotRepository inquiryWriterSnapshotRepository;

	@EventListener
	public void updateInquiryWriterSnapshot(UserUpdatedEvent event) {
		InquiryWriterSnapshot snapshot = InquiryWriterSnapshot.builder()
			.name(event.name())
			.company(event.company())
			.phoneNumber(event.phoneNumber())
			.userEmail(event.email())
			.build();

		inquiryWriterSnapshotRepository.save(snapshot);
	}
}
