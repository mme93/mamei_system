import {Injectable} from '@angular/core';
import {StepperProcessUI} from "../../../../model/admin/process/ProcessUIElements";
import {Process} from "../../../../model/admin/process/ProcessApiEntity";
import {ProcessService} from "../process.service";
import {MatCheckboxChange} from "@angular/material/checkbox";

@Injectable({
  providedIn: 'root'
})
export class ProcessStepperService {

  constructor(private databaseProcessService: ProcessService) {
  }

  createProcessStepperUI(): StepperProcessUI {
    return {
      processStatusIcon: ['play_disabled', 'play_arrow', 'build', 'done', 'error'],
      processStepText: 'Choose your steps for Database Processes',
      defaultProcessStepText: 'Choose your steps for Database Processes',
      canExecute: false,
      canDisplay: false,
      firstStepProcessUI: {
        canStartProcess: false,
        isProcessSelected: false,
        processList: [],
        copyProcessList: [],
      },
      secondStepProcessUI: {
        incr: 0,
        progress: 0,
        executeTaskSignature: '',
        isLoading: false,
        isProcessRunning: false,
        isProcessFinish: false,
        executeProcessUI: {
          signature: '',
          executeMainProcesses: [],
          mainProcessAmount: 0,
          processDuration: '',
          isProcessFinish: false,
          isProcessRunning: false
        },
        startProcessList: []
      },
      lastStepProcessUI: {
        protocolResultUI: {
          id: 0,
          executeTaskDate: '',
          executeEndTaskDate: '',
          signature: '',
          mainProcessAmount: '',
          subProcessAmount: '',
          totalProcessAmount: '',
          processDuration: '',
          etaskProcessStatus: '',
          executeTaskUser: '',
          userComment: '',
          protocolMainResults: []
        },
        userComment: {
          userComment: '',
          taskSignature: '',
        }
      }
    }
  };

  resetProcessSelection(stepperProcessUI: StepperProcessUI): StepperProcessUI {
    stepperProcessUI.firstStepProcessUI.processList.forEach(process => {
      process.processStatusIcon = stepperProcessUI.processStatusIcon[0];
      process.processActivated = false;
    })
    return stepperProcessUI;
  }

  resetExecuteProcess(stepperProcessUI: StepperProcessUI): StepperProcessUI {
    this.resetProcessSelection(stepperProcessUI);

    stepperProcessUI.lastStepProcessUI.protocolResultUI = {
      id: 0,
      executeTaskDate: '',
      executeEndTaskDate: '',
      signature: '',
      mainProcessAmount: '',
      subProcessAmount: '',
      totalProcessAmount: '',
      processDuration: '',
      etaskProcessStatus: '',
      executeTaskUser: '',
      userComment: '',
      protocolMainResults: []
    };
    stepperProcessUI.secondStepProcessUI.executeTaskSignature = "";
    stepperProcessUI.firstStepProcessUI.isProcessSelected = true;
    stepperProcessUI.secondStepProcessUI.startProcessList = [];
    stepperProcessUI.secondStepProcessUI.isLoading = false;
    stepperProcessUI.processStepText = 'Choose your steps for Database Processes';
    stepperProcessUI.secondStepProcessUI.isProcessRunning = false;
    stepperProcessUI.secondStepProcessUI.isProcessFinish = false;
    stepperProcessUI.secondStepProcessUI.executeProcessUI = {
      signature: '',
      executeMainProcesses: [],
      mainProcessAmount: 0,
      processDuration: '',
      isProcessFinish: false,
      isProcessRunning: false
    };
    stepperProcessUI.canExecute = false;
    stepperProcessUI.canDisplay = false
    stepperProcessUI.lastStepProcessUI.userComment.userComment = '';
    this.databaseProcessService.getProcesses().subscribe((process: Process[]) => {
      stepperProcessUI.firstStepProcessUI.processList = this.databaseProcessService.getProcess(process);
      stepperProcessUI.firstStepProcessUI.copyProcessList = this.databaseProcessService.getProcess(process)
    });
    return stepperProcessUI;
  }

  newProcess(stepperProcessUI: StepperProcessUI): StepperProcessUI {
    return this.resetExecuteProcess(stepperProcessUI);
  }

  changeIcon(microServiceName: string, $event: MatCheckboxChange,stepperProcessUI: StepperProcessUI): StepperProcessUI {
    for (let i = 0; i < stepperProcessUI.firstStepProcessUI.processList.length; i++) {
      if (stepperProcessUI.firstStepProcessUI.processList[i].process.processName === microServiceName) {
        if ($event.checked) {
          stepperProcessUI.firstStepProcessUI.processList[i].processStatusIcon = stepperProcessUI.processStatusIcon[1];
          stepperProcessUI.firstStepProcessUI.copyProcessList[i].processStatusIcon = stepperProcessUI.processStatusIcon[1];
        } else {
          stepperProcessUI.firstStepProcessUI.copyProcessList[i].processStatusIcon = stepperProcessUI.processStatusIcon[0];
          stepperProcessUI.firstStepProcessUI.processList[i].processStatusIcon = stepperProcessUI.processStatusIcon[0];
        }
        stepperProcessUI.firstStepProcessUI.copyProcessList[i].processActivated = stepperProcessUI.firstStepProcessUI.processList[i].processActivated;
        stepperProcessUI.firstStepProcessUI.processList[i].processActivated = $event.checked;
      }
    }
    stepperProcessUI.firstStepProcessUI.copyProcessList.forEach(process => {
      if (process.processStatusIcon === stepperProcessUI.processStatusIcon[0]) {
        process.processActivated = false;
      }
    });
    stepperProcessUI.firstStepProcessUI.isProcessSelected = !(stepperProcessUI.firstStepProcessUI.copyProcessList.filter(process => process.processStatusIcon === stepperProcessUI.processStatusIcon[1]).length > 0);
    stepperProcessUI.canExecute = stepperProcessUI.firstStepProcessUI.copyProcessList.filter(process => process.processStatusIcon === stepperProcessUI.processStatusIcon[1]).length > 0;
    return stepperProcessUI;
  }

}
