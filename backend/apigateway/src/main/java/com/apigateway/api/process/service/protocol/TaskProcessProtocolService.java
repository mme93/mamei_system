package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.model.protocol.*;
import com.apigateway.api.process.repository.ProcessProtocolRepository;
import com.apigateway.api.process.repository.TaskProcessProtocolRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;


@Service
public class TaskProcessProtocolService {

    private final TaskProcessProtocolRepository taskProcessProtocolRepository;
    private final ProcessProtocolRepository processProtocolRepository;
    private final String dateTimePattern = "yyyy-MM-dd/HH:mm:ss.SS";


    @Autowired
    public TaskProcessProtocolService(TaskProcessProtocolRepository taskProcessProtocolRepository, ProcessProtocolRepository processProtocolRepository) {
        this.taskProcessProtocolRepository = taskProcessProtocolRepository;
        this.processProtocolRepository = processProtocolRepository;
    }

    public TaskProcessProtocol getTaskProcessProtocol(String task_signature) {
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(task_signature);
        if (!taskProcessProtocolOpt.isPresent()) {
            throw new NotFoundException("No Task Process Protocol found by signature: " + task_signature);
        }
        TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();
        //taskProcessProtocol.setProcessProtocols(processProtocolRepository.findAllByParent_signature(taskProcessProtocol.getSignature()));
        return taskProcessProtocol;
    }

    public void createTaskProtocol(String task_signature, String userName) {
        taskProcessProtocolRepository.save(new TaskProcessProtocol(
                generateLocalTimeDate(),
                null,
                task_signature,
                null,
                null,
                null,
                null,
                null,
                userName,
                null,
                asList()
        ));

    }

    public String generateLocalTimeDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return currentDateTime.format(formatter);
    }

    public String getDuration(String startLocalDateTime, String endLocalDateTime) {
        LocalDateTime dateTime1 = LocalDateTime.parse(startLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        LocalDateTime dateTime2 = LocalDateTime.parse(endLocalDateTime, DateTimeFormatter.ofPattern(dateTimePattern));
        Duration duration = Duration.between(dateTime1, dateTime2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(duration.toHours() + ":");
        stringBuilder.append(duration.toMinutesPart() + ":");
        stringBuilder.append(duration.toSecondsPart() + ":");
        stringBuilder.append(duration.toMillisPart());
        return stringBuilder.toString();
    }

    public void closeTaskProtocol(String task_signature) {
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(task_signature);
        if (!taskProcessProtocolOpt.isPresent()) {
            throw new NotFoundException("No Task Process Protocol found by signature: " + task_signature);
        }
        TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();
        List<ProcessProtocol> processProtocols = processProtocolRepository.findAll().stream()
                .filter(processProtocol -> processProtocol.getParent_signature().equals(task_signature)).collect(Collectors.toList());
        List<Long> amount = getAmountListForProcessTask(processProtocols);
        String dateTime = generateLocalTimeDate();
        taskProcessProtocol.setMainProcessAmount(amount.get(0).toString());
        taskProcessProtocol.setSubProcessAmount(amount.get(1).toString());
        taskProcessProtocol.setTotalProcessAmount(amount.get(2).toString());
        taskProcessProtocol.setETaskProcessStatus(getTaskProcessStatus(processProtocols));
        taskProcessProtocol.setExecuteEndTaskDate(dateTime);
        taskProcessProtocol.setProcessDuration(getDuration(dateTime, taskProcessProtocol.getExecuteTaskDate()));
        taskProcessProtocolRepository.save(taskProcessProtocol);
    }

    public List<Long> getAmountListForProcessTask(List<ProcessProtocol> processProtocols) {
        List<Long> amount = new ArrayList<>();
        long mainProcessAmount = processProtocols.stream().filter(processProtocol ->
                processProtocol.getEProcessTypProtocol().equals(EProcessTypProtocol.MAIN)).count();
        amount.add(mainProcessAmount);
        long subProcessAmount = processProtocols.stream().filter(processProtocol ->
                processProtocol.getEProcessTypProtocol().equals(EProcessTypProtocol.SUB)).count();
        amount.add(subProcessAmount);
        amount.add(mainProcessAmount + subProcessAmount);
        return amount;
    }

    public ETaskProcessStatus getTaskProcessStatus(List<ProcessProtocol> processProtocols) {
        long failedAmount = processProtocols.stream().
                filter(processProtocol -> processProtocol.getEProcessStatus().equals(EProcessStatus.FAILED)).count();
        if (failedAmount > 0) {
            return ETaskProcessStatus.RESTRICTED;
        }
        return ETaskProcessStatus.UNRESTRICTED;
    }

    public List<TaskProcessProtocol> getTaskProcessProtocols() {
        return taskProcessProtocolRepository.findAll();
    }
}