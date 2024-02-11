import {ProcessUI, ProtocolResultUI, UserComment} from "./ProcessApiEntity";

export interface StepperProcessUI {
  processStatusIcon: string[];
  processStepText: string;
  defaultProcessStepText: string;
  firstStepProcessUI: FirstStepProcessUI;
  secondStepProcessUI: SecondStepProcessUI;
  lastStepProcessUI: LastStepProcessUI
}

export interface FirstStepProcessUI {
  canStartProcess: boolean;
  isProcessSelected: boolean;
  processList: ProcessUI[];
  copyProcessList: ProcessUI[];
}

export interface SecondStepProcessUI {

}

export interface LastStepProcessUI {
  protocolResultUI: ProtocolResultUI;
  userComment: UserComment;
}
