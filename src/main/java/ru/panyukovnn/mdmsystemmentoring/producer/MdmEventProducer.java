package ru.panyukovnn.mdmsystemmentoring.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.panyukovnn.mdmsystemmentoring.dto.MdmEventDto;
import ru.panyukovnn.mdmsystemmentoring.util.JsonUtil;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "mdm.kafka.mdm-event", name = "enabled", havingValue = "true")
public class MdmEventProducer {

    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${mdm.kafka.mdm-event.topic-in}")
    private String topicIn;

    public void send(MdmEventDto mdmEventDto) {
        kafkaTemplate.send(topicIn, jsonUtil.toJson(mdmEventDto))
            .thenAccept(sendResult -> log.info("Mdm событие отправлено в кафку: {}", sendResult));
    }
}
