import {ProcessUI} from "./ProcessApiEntity";

export interface StepperProcessUI {
  processStatusIcon: string[];
  processStepText: string;
  defaultProcessStepText: string;
  firstStepProcessUI: FirstStepProcessUI;
  secondStepProcessUI: SecondStepProcessUI;
}

export interface FirstStepProcessUI {
  canStartProcess: boolean;
  processList: ProcessUI[];
}

export interface SecondStepProcessUI {

}
