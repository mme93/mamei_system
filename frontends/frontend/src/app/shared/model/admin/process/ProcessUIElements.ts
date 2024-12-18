import {ExecuteProcessUI, ProcessUI, ProtocolResultUI, UserComment} from "./ProcessApiEntity";

export interface StepperProcessUI {
  processStatusIcon: string[];
  processStepText: string;
  defaultProcessStepText: string;
  canExecute: boolean;
  canDisplay: boolean;
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
  incr: number;
  progress: number;
  executeTaskSignature: string;
  isLoading: boolean;
  isProcessRunning: boolean;
  isProcessFinish: boolean;
  executeProcessUI: ExecuteProcessUI;
  startProcessList: ProcessUI[];
}

export interface LastStepProcessUI {
  protocolResultUI: ProtocolResultUI;
  userComment: UserComment;
}
