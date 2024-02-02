package com.apigateway.util;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LocalDateTimeFactory {

    private final String dateTimePattern = "yyyy-MM-dd/HH:mm:ss.SS";

    public String generateLocalTimeDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return currentDateTime.format(formatter);
    }

    public String getDuration(String startLocalDateTime, String endLocalDateTime) {
        LocalDateTime start = LocalDateTime.parse(startLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        LocalDateTime end = LocalDateTime.parse(endLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        Duration duration = Duration.between(end, start);
        StringBuilder stringBuilder = new StringBuilder();
        if (duration.toHours()<10) {
            stringBuilder.append("0"+duration.toHours()+":");
        } else {
            stringBuilder.append(duration.toHours() + ":");
        }
        if (duration.toMinutesPart() <10) {
            stringBuilder.append("0"+duration.toMinutesPart()+":");
        } else {
            stringBuilder.append(duration.toMinutesPart() + ":");
        }
        if (duration.toSecondsPart() <10) {
            stringBuilder.append("0"+duration.toSecondsPart()+":");
        } else {
            stringBuilder.append(duration.toSecondsPart() + ":");
        }
        if (duration.toMillisPart() < 10) {
            stringBuilder.append("00"+duration.toMillisPart() + ":");
        }else if (duration.toMillisPart() < 100) {
            stringBuilder.append("0" + duration.toMillisPart() + ":");
        } else {
            stringBuilder.append(duration.toMillisPart());
        }
        return stringBuilder.toString();
    }


}
