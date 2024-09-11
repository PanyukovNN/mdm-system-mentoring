package ru.panyukovnn.mdmsystemmentoring.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.panyukovnn.mdmsystemmentoring.service.MdmEventService;

@Service
@RequiredArgsConstructor
public class CleanOldRecordsJob {

    private final MdmEventService mdmEventService;

    @Async("cleanOldRecordsExecutor")
    @Scheduled(cron = "${mdm.event.clean-old-records.cron}")
    public void sendUpdatePhoneEvent() {
        mdmEventService.cleanOldRecords();
    }
}
