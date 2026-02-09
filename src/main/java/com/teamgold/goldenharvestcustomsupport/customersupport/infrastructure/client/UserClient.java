package com.teamgold.goldenharvestcustomsupport.customersupport.infrastructure.client;

import com.teamgold.goldenharvestcustomsupport.common.response.ApiResponse;
import com.teamgold.goldenharvestcustomsupport.customersupport.infrastructure.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://user-service:8082").build();
    }

    public UserResponse getUserAttributes(String userId) {
        try {
            ApiResponse<UserResponse> response = restClient.get()
                    .uri("/api/internal/users/{userId}", userId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            if (response != null && response.isSuccess() && response.getData() != null) {
                return response.getData();
            }
        } catch (Exception e) {
            log.error("Failed to fetch user attributes for userId: {}", userId, e);
        }
        return null;
    }
}
