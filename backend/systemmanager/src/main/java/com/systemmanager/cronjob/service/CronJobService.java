package com.systemmanager.cronjob.service;

import com.systemmanager.util.LocalDateTimeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CronJobService {

    private final LocalDateTimeFactory localDateTimeFactory;

    @Autowired
    public CronJobService(LocalDateTimeFactory localDateTimeFactory) {
        this.localDateTimeFactory = localDateTimeFactory;
    }

    /*
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void doSomething() {
        System.err.println(localDateTimeFactory.generateLocalTimeDate());
    }
     */

}
