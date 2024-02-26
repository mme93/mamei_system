package com.systemmanager.util;

import com.systemmanager.util.exception.LocalTimeNullPointerException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for generating and manipulating LocalDateTime objects.
 */
@Service
public class LocalDateTimeFactory {

    private final String dateTimePattern = "yyyy-MM-dd/HH:mm:ss.SS";

    /**
     * Generates a formatted string representing the current LocalDateTime.
     *
     * @return The formatted string representing the current LocalDateTime.
     */
    public String generateLocalTimeDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return currentDateTime.format(formatter);
    }

    /**
     * Calculates the duration between two LocalDateTime instances and returns it as a formatted string.
     *
     * @param startLocalDateTime The start LocalDateTime string.
     * @param endLocalDateTime   The end LocalDateTime string.
     * @return The duration between the two LocalDateTime instances as a formatted string (HH:mm:ss.SSS).
     */
    public String getDuration(String startLocalDateTime, String endLocalDateTime) {

        if (startLocalDateTime == null || endLocalDateTime == null) {
            throw new LocalTimeNullPointerException("startLocalDateTime or endLocalDateTime: "+startLocalDateTime
            +" | "+endLocalDateTime);
        }

        LocalDateTime start = LocalDateTime.parse(startLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        LocalDateTime end = LocalDateTime.parse(endLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        Duration duration;
        if (end.isAfter(start)) {
            duration = Duration.between(start, end);
        } else {
            duration = Duration.between(end, start);
        }

        StringBuilder stringBuilder = new StringBuilder();
      try{
          if (duration.toHours() < 10) {
              stringBuilder.append("0" + duration.toHours() + ":");
          } else {
              stringBuilder.append(duration.toHours() + ":");
          }
          if (duration.toMinutesPart() < 10) {
              stringBuilder.append("0" + duration.toMinutesPart() + ":");
          } else {
              stringBuilder.append(duration.toMinutesPart() + ":");
          }
          if (duration.toSecondsPart() < 10) {
              stringBuilder.append("0" + duration.toSecondsPart() + ":");
          } else {
              stringBuilder.append(duration.toSecondsPart() + ":");
          }
          if (duration.toMillisPart() < 10) {
              stringBuilder.append("00" + duration.toMillisPart());
          } else if (duration.toMillisPart() < 100) {
              stringBuilder.append("0" + duration.toMillisPart());
          } else {
              stringBuilder.append(duration.toMillisPart());
          }
      }catch (DateTimeParseException e){
          throw new IllegalArgumentException(e.getMessage());
      }
        return stringBuilder.toString();
    }


}
