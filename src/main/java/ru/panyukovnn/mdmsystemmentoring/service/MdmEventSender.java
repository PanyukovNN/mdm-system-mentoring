package ru.panyukovnn.mdmsystemmentoring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.panyukovnn.mdmsystemmentoring.dto.MdmEventDto;
import ru.panyukovnn.mdmsystemmentoring.producer.MdmEventProducer;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MdmEventSender {

    private final MdmEventService mdmEventService;
    private final MdmEventProducer mdmEventProducer;

    public void sendUpdatePhoneEvent() {
        MdmEventDto mdmEventDto = MdmEventDto.builder()
            .id(UUID.randomUUID())
            .guid(UUID.randomUUID().toString().replace("-", "").toUpperCase())
            .phone("+79" + RandomStringUtils.randomNumeric(9))
            .build();

        mdmEventProducer.send(mdmEventDto);
        mdmEventService.saveMdmEvent(mdmEventDto);
    }
}
