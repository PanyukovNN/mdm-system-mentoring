package ru.panyukovnn.mdmsystemmentoring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponseBody;
import ru.panyukovnn.mdmsystemmentoring.dto.userdataserviceone.UserDataServiceOneRequest;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponse;
import ru.panyukovnn.mdmsystemmentoring.service.MdmEventService;

import java.util.UUID;

import static ru.panyukovnn.mdmsystemmentoring.util.Constants.ERROR_RESPONSE_STATUS;
import static ru.panyukovnn.mdmsystemmentoring.util.Constants.SUCCESS_RESPONSE_STATUS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-data-service-one")
public class UserDataServiceOneController {

    private final MdmEventService mdmEventService;

    @PostMapping("/update-phone")
    public ResponseEntity<CommonResponse> updatePhone(@RequestBody @Valid UserDataServiceOneRequest request) {
        if (Math.random() <= .2) {
            return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(CommonResponse.builder()
                    .body(CommonResponseBody.builder()
                        .id(UUID.randomUUID())
                        .status(ERROR_RESPONSE_STATUS)
                        .errorMessage("Превышено допустимое количество запросов")
                        .build())
                    .build());
        }

        mdmEventService.saveUserServiceOneEvent(request);

        return ResponseEntity
            .ok(CommonResponse.builder()
                .body(CommonResponseBody.builder()
                    .id(UUID.randomUUID())
                    .status(SUCCESS_RESPONSE_STATUS)
                    .build())
                .build());
    }
}
