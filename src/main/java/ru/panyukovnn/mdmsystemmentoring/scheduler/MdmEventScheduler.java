package ru.panyukovnn.mdmsystemmentoring.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.panyukovnn.mdmsystemmentoring.service.MdmEventSender;

@Service
@RequiredArgsConstructor
public class MdmEventScheduler {

    private final MdmEventSender mdmEventSender;

    @Async("mdmEventSendingExecutor")
    @Scheduled(cron = "${mdm.event.update-phone.cron}")
    public void sendUpdatePhoneEvent() {
        mdmEventSender.sendUpdatePhoneEvent();
    }
}
