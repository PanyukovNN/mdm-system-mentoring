package ru.panyukovnn.mdmsystemmentoring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.mdmsystemmentoring.dto.MdmEventDto;
import ru.panyukovnn.mdmsystemmentoring.dto.userdataserviceone.UserDataServiceOneRequest;
import ru.panyukovnn.mdmsystemmentoring.dto.userdataservicetwo.UserDataServiceTwoMdmEvent;
import ru.panyukovnn.mdmsystemmentoring.dto.userdataservicetwo.UserDataServiceTwoRequest;
import ru.panyukovnn.mdmsystemmentoring.model.MdmEvent;
import ru.panyukovnn.mdmsystemmentoring.model.MdmUserDataServiceEvent;
import ru.panyukovnn.mdmsystemmentoring.model.UserDataServiceId;
import ru.panyukovnn.mdmsystemmentoring.repository.MdmEventRepository;
import ru.panyukovnn.mdmsystemmentoring.repository.MdmUserDataServiceEventRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MdmEventService {

    private final MdmEventRepository mdmEventRepository;
    private final MdmUserDataServiceEventRepository mdmUserDataServiceEventRepository;

    public void saveMdmEvent(MdmEventDto mdmEventDto) {
        MdmEvent mdmEvent = MdmEvent.builder()
            .type(mdmEventDto.getType())
            .guid(mdmEventDto.getGuid())
            .phone(mdmEventDto.getPhone())
            .build();

        mdmEventRepository.save(mdmEvent);
    }

    public void saveUserServiceOneEvent(UserDataServiceOneRequest request) {
        MdmUserDataServiceEvent mdmUserDataServiceEvent = MdmUserDataServiceEvent.builder()
            .guid(request.getBody().getGuid())
            .phone(request.getBody().getPhone())
            .systemId(request.getMeta().getSystemId())
            .serviceId(UserDataServiceId.USER_DATA_SERVICE_ONE)
            .build();

        mdmUserDataServiceEventRepository.save(mdmUserDataServiceEvent);
    }

    public void saveUserServiceTwoEvent(UserDataServiceTwoRequest request) {
        UserDataServiceTwoMdmEvent event = request.getEvents().get(0);

        MdmUserDataServiceEvent mdmUserDataServiceEvent = MdmUserDataServiceEvent.builder()
            .type(event.getEventType())
            .guid(event.getGuid())
            .phone(event.getPhone())
            .systemId(request.getSystemId())
            .serviceId(UserDataServiceId.USER_DATA_SERVICE_TWO)
            .build();

        mdmUserDataServiceEventRepository.save(mdmUserDataServiceEvent);
    }

    public void cleanOldRecords() {
        LocalDateTime createDateTimeBefore = LocalDateTime.now().minusDays(7);

        mdmEventRepository.deleteByCreateTimeBefore(createDateTimeBefore);
        mdmUserDataServiceEventRepository.deleteByCreateTimeBefore(createDateTimeBefore);
    }
}
