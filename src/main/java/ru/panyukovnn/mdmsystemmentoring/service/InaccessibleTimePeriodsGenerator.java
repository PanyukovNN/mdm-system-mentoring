package ru.panyukovnn.mdmsystemmentoring.service;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InaccessibleTimePeriodsGenerator {

    private static final Random random = new Random();
    private volatile LocalDate currentDate;
    private volatile List<Pair<LocalDateTime, LocalDateTime>> inaccessibleTimePeriods;

    /**
     * Генерирует временные периоды, в которые сервис будет недоступен
     *
     * @return пары обозначающие начало и конец временного периода
     */
    public List<Pair<LocalDateTime, LocalDateTime>> getTodayInaccessiblePeriods() {
        if (!LocalDate.now().equals(currentDate)) {
            currentDate = LocalDate.now();

            generateInaccessiblePeriods();
        }

        return inaccessibleTimePeriods;
    }

    private void generateInaccessiblePeriods() {
        List<Integer> hours = IntStream.range(0, 24).boxed()
            .collect(Collectors.toList());
        Collections.shuffle(hours);

        List<Integer> inaccessibleHours = hours.subList(0, 12);
        List<Pair<LocalDateTime, LocalDateTime>> inaccessibleTimePeriods = new ArrayList<>();

        for (Integer hour : inaccessibleHours) {
            int durationMins = random.nextInt(32) + 9;
            int startMinute = random.nextInt(61 - durationMins);

            LocalDateTime startPeriod = LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, startMinute));
            LocalDateTime endPeriod = startPeriod.plusMinutes(durationMins);

            inaccessibleTimePeriods.add(Pair.of(startPeriod, endPeriod));
        }

        this.inaccessibleTimePeriods = inaccessibleTimePeriods;
    }
}
