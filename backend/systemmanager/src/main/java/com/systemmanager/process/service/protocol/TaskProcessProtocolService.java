package com.systemmanager.process.service.protocol;

import com.systemmanager.process.model.protocol.*;
import com.systemmanager.process.model.protocol.ui.ProtocolMainResult;
import com.systemmanager.process.model.protocol.ui.ProtocolResultUI;
import com.systemmanager.process.model.protocol.ui.ProtocolSubResult;
import com.systemmanager.process.repository.ProcessProtocolRepository;
import com.systemmanager.process.repository.TaskProcessProtocolRepository;
import com.systemmanager.util.LocalDateTimeFactory;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ProtocolResultUI getTaskProcessProtocol(String signature) {
        ProtocolResultUI protocolResultUI = null;
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(signature);
        if (taskProcessProtocolOpt.isPresent()) {
            TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();
            List<ProcessProtocol> processMainProtocols = processProtocolRepository.findAllByParentSignature(taskProcessProtocol.getSignature());
            List<ProtocolMainResult> protocolMainResults = convertToProtocolMainResult(processMainProtocols);
            protocolResultUI = new ProtocolResultUI(
                    taskProcessProtocol.getId(),
                    taskProcessProtocol.getExecuteTaskDate(),
                    taskProcessProtocol.getExecuteEndTaskDate(),
                    taskProcessProtocol.getSignature(),
                    taskProcessProtocol.getMainProcessAmount(),
                    taskProcessProtocol.getSubProcessAmount(),
                    taskProcessProtocol.getTotalProcessAmount(),
                    taskProcessProtocol.getProcessDuration(),
                    taskProcessProtocol.getETaskProcessStatus(),
                    taskProcessProtocol.getExecuteTaskUser(),
                    taskProcessProtocol.getUserComment(),
                    protocolMainResults
            );
        }

        return protocolResultUI;
    }

    public List<ProtocolMainResult> convertToProtocolMainResult(List<ProcessProtocol> processProtocols) {
        List<ProtocolMainResult> protocolMainResults = new ArrayList<>();

        for (ProcessProtocol processProtocol : processProtocols) {
            protocolMainResults.add(new ProtocolMainResult(
                    processProtocol.getId(),
                    processProtocol.getSignature(),
                    processProtocol.getParentSignature(),
                    processProtocol.getProcessName(),
                    processProtocol.getProcessText(),
                    processProtocol.getExecuteProcessDate(),
                    processProtocol.getExecuteEndProcessDate(),
                    processProtocol.getEProcessTypProtocol(),
                    processProtocol.getEProcessStatus(),
                    processProtocol.getResult(),
                    createSubProtocol(processProtocolRepository.findAllByParentSignature(processProtocol.getSignature()))
            ));
        }
        return protocolMainResults;
    }

    private List<ProtocolSubResult> createSubProtocol(List<ProcessProtocol> processSubProtocols) {
        List<ProtocolSubResult> protocolSubResults = new ArrayList<>();
        for (ProcessProtocol processSubProtocol : processSubProtocols) {
            protocolSubResults.add(new ProtocolSubResult(
                    processSubProtocol.getId(),
                    processSubProtocol.getSignature(),
                    processSubProtocol.getParentSignature(),
                    processSubProtocol.getProcessName(),
                    processSubProtocol.getProcessText(),
                    processSubProtocol.getExecuteProcessDate(),
                    processSubProtocol.getExecuteEndProcessDate(),
                    processSubProtocol.getEProcessTypProtocol(),
                    processSubProtocol.getEProcessStatus(),
                    processSubProtocol.getResult()
            ));
        }
        return protocolSubResults;
    }

    public void updateUserComment(ProtocolUserComment userComment) {
        Optional<TaskProcessProtocol> taskOpt = taskProcessProtocolRepository.findBySignature(userComment.getTaskSignature());
        if(taskOpt.isPresent()){
            TaskProcessProtocol taskProcessProtocol = taskOpt.get();
            taskProcessProtocol.setUserComment(userComment.getUserComment());
            taskProcessProtocolRepository.save(taskProcessProtocol);
        }
    }
}
