package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.model.protocol.*;
import com.apigateway.api.process.repository.ProcessProtocolRepository;
import com.apigateway.api.process.repository.TaskProcessProtocolRepository;
import com.apigateway.util.LocalDateTimeFactory;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;


@Service
public class TaskProcessProtocolService {

    private final TaskProcessProtocolRepository taskProcessProtocolRepository;
    private final ProcessProtocolRepository processProtocolRepository;
    private final LocalDateTimeFactory localDateTimeFactory;


    @Autowired
    public TaskProcessProtocolService(TaskProcessProtocolRepository taskProcessProtocolRepository, ProcessProtocolRepository processProtocolRepository, LocalDateTimeFactory localDateTimeFactory) {
        this.taskProcessProtocolRepository = taskProcessProtocolRepository;
        this.processProtocolRepository = processProtocolRepository;
        this.localDateTimeFactory = localDateTimeFactory;
    }

    public TaskProcessProtocol getTaskProcessProtocol(String task_signature) {
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(task_signature);
        if (!taskProcessProtocolOpt.isPresent()) {
            throw new NotFoundException("No Task Process Protocol found by signature: " + task_signature);
        }
        TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();
        taskProcessProtocol.setProcessProtocols(processProtocolRepository.findAllByParentSignature(taskProcessProtocol.getSignature()));
        return taskProcessProtocol;
    }

    public void createTaskProtocol(String task_signature, String userName) {
        taskProcessProtocolRepository.save(new TaskProcessProtocol(
                localDateTimeFactory.generateLocalTimeDate(),
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

    public void closeTaskProtocol(String task_signature) {
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(task_signature);
        if (!taskProcessProtocolOpt.isPresent()) {
            throw new NotFoundException("No Task Process Protocol found by signature: " + task_signature);
        }
        TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();
        List<ProcessProtocol> processProtocols = processProtocolRepository.findAllByParentSignature(task_signature);
        List<Long> amount = getAmountListForProcessTask(processProtocols);
        String dateTime = localDateTimeFactory.generateLocalTimeDate();
        taskProcessProtocol.setMainProcessAmount(amount.get(0).toString());
        taskProcessProtocol.setSubProcessAmount(amount.get(1).toString());
        taskProcessProtocol.setTotalProcessAmount(amount.get(2).toString());
        taskProcessProtocol.setETaskProcessStatus(getTaskProcessStatus(processProtocols));
        taskProcessProtocol.setExecuteEndTaskDate(dateTime);
        taskProcessProtocol.setProcessDuration(localDateTimeFactory.getDuration(dateTime, taskProcessProtocol.getExecuteTaskDate()));
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
