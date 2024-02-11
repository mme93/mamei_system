import {Injectable} from '@angular/core';
import {StepperProcessUI} from "../../../../model/admin/process/ProcessUIElements";

@Injectable({
  providedIn: 'root'
})
export class ProcessStepperService {

  createProcessStepperUI(): StepperProcessUI {
    return {
      processStatusIcon: ['play_disabled', 'play_arrow', 'build', 'done', 'error'],
      processStepText: 'Choose your steps for Database Processes',
      defaultProcessStepText: 'Choose your steps for Database Processes',
      firstStepProcessUI: {
        canStartProcess: false,
        isProcessSelected: false,
        processList: [],
        copyProcessList: [],
      },
      secondStepProcessUI: {
        executeProcessUI: {
          signature: '',
          executeMainProcesses: [],
          mainProcessAmount: 0,
          processDuration: '',
          isProcessFinish: false,
          isProcessRunning: false
        }
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

  loadFirStepperInformation(stepperProcessUI: StepperProcessUI): StepperProcessUI {

    return stepperProcessUI;
  }

}
