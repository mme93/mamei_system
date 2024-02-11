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
        processList: [],
      },
      secondStepProcessUI: {}
    }
  };

  loadFirStepperInformation(stepperProcessUI: StepperProcessUI): StepperProcessUI {

    return stepperProcessUI;
  }

}
