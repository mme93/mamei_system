package com.systemmanager.util;

import com.systemmanager.util.exception.LocalTimeNullPointerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Unit tests for the LocalDateTimeFactory class.
 */
@SpringBootTest(classes = {LocalDateTimeFactory.class})
public class LocalDateTimeFactoryTest {

    @Autowired
    @InjectMocks
    private LocalDateTimeFactory localDateTimeFactory;

    /**
     * Initializes Mockito annotations before each test method.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the generateLocalTimeDate method to validate the format of the generated local date and time string.
     */
    @Test
    public void testGenerateLocalTimeDateValidateFormat() {
        String resultLocalTimeDate = localDateTimeFactory.generateLocalTimeDate();
        Assertions.assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss.SS")), resultLocalTimeDate);
    }

    /**
     * Tests the getDuration method with valid start and end date times.
     */
    @Test
    public void testGetDurationOnlyTime() {
        String startDate = "2024-02-25/13:40:00.13";
        String endDate = "2024-02-25/22:41:43.00";
        String expectedDuration = "09:01:42:870";
        String resultDuration = localDateTimeFactory.getDuration(startDate, endDate);
        Assertions.assertEquals(resultDuration, expectedDuration);
    }

    /**
     * Tests the getDuration method with start and end dates differing by exactly one year.
     */
    @Test
    public void testGetDurationOnlyDate() {
        String startDate = "2024-02-25/12:00:00.00";
        String endDate = "2023-02-25/12:00:00.00";
        String expectedDurationOneYearAsHour = "8760:00:00:000";
        String resultDuration = localDateTimeFactory.getDuration(startDate, endDate);
        Assertions.assertEquals(resultDuration, expectedDurationOneYearAsHour);
    }

    /**
     * Tests the getDuration method with invalid date-time format, expecting a DateTimeParseException.
     */
    @Test
    public void testGetDurationWithDateTimeParseException() {
        String startDate = "2024-02-25/12:01:00.00";
        String endDate = "2023-02-25/12:00:0X.00";
        DateTimeParseException thrown = Assertions.assertThrows(DateTimeParseException.class, () ->
                localDateTimeFactory.getDuration(startDate, endDate), "DateTimeParseException was expected");
        Assertions.assertEquals(
                "Text '2023-02-25/12:00:0X.00' could not be parsed at index 17"
                , thrown.getMessage());
    }

    /**
     * Tests the getDuration method with null start and end date-time strings, expecting a LocalTimeNullPointerException.
     */
    @Test
    public void testGetDurationWithNullPointerException() {
        LocalTimeNullPointerException thrown = Assertions.assertThrows(LocalTimeNullPointerException.class, () ->
                localDateTimeFactory.getDuration(null, null), "LocalTimeNullPointerException was expected");
        Assertions.assertEquals(
                "startLocalDateTime or endLocalDateTime: null | null"
                , thrown.getMessage());
    }

}
