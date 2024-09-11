package ru.panyukovnn.mdmsystemmentoring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponse;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponseBody;
import ru.panyukovnn.mdmsystemmentoring.dto.userdataservicetwo.UserDataServiceTwoRequest;
import ru.panyukovnn.mdmsystemmentoring.service.InaccessibleTimePeriodsGenerator;
import ru.panyukovnn.mdmsystemmentoring.service.MdmEventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ru.panyukovnn.mdmsystemmentoring.util.Constants.ERROR_RESPONSE_STATUS;
import static ru.panyukovnn.mdmsystemmentoring.util.Constants.SUCCESS_RESPONSE_STATUS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-data-service-two")
public class UserDataServiceTwoController {

    private final MdmEventService mdmEventService;
    private final InaccessibleTimePeriodsGenerator inaccessibleTimePeriodsGenerator;

    @PostMapping("/user/update/phone")
    public ResponseEntity<CommonResponse> updatePhone(@RequestBody @Valid UserDataServiceTwoRequest request) {
        List<Pair<LocalDateTime, LocalDateTime>> todayInaccessiblePeriods = inaccessibleTimePeriodsGenerator.getTodayInaccessiblePeriods();

        LocalDateTime now = LocalDateTime.now();
        boolean isInaccessible = todayInaccessiblePeriods.stream().anyMatch(pair -> {
            LocalDateTime startPeriod = pair.getLeft();
            LocalDateTime endPeriod = pair.getRight();

            return now.isAfter(startPeriod) && now.isBefore(endPeriod);
        });

        if (isInaccessible) {
            return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(CommonResponse.builder()
                    .body(CommonResponseBody.builder()
                        .id(UUID.randomUUID())
                        .status(ERROR_RESPONSE_STATUS)
                        .errorMessage("Сервис недоступен")
                        .build())
                    .build());
        }

        mdmEventService.saveUserServiceTwoEvent(request);

        return ResponseEntity
            .ok(CommonResponse.builder()
                .body(CommonResponseBody.builder()
                    .id(UUID.randomUUID())
                    .status(SUCCESS_RESPONSE_STATUS)
                    .build())
                .build());
    }
}
