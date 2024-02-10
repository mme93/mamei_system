// ProtocolSubResult.ts
export interface ProtocolSubResult {
  id: number;
  signature: string;
  parentSignature: string;
  processName: string;
  processText: string;
  executeProcessDate: string;
  executeEndProcessDate: string;
  eprocessTypProtocol: string;
  eprocessStatus: string;
  result: string;
}

// ProtocolMainResult.ts
export interface ProtocolMainResult {
  id: number;
  signature: string;
  parentSignature: string;
  processName: string;
  processText: string;
  executeProcessDate: string;
  executeEndProcessDate: string;
  eprocessTypProtocol: string;
  eprocessStatus: string;
  result: string;
  protocolSubResults: ProtocolSubResult[];
}

// ProtocolResultUI.ts
export interface ProtocolResultUI {
  id: number;
  executeTaskDate: string;
  executeEndTaskDate: string;
  signature: string;
  mainProcessAmount: string;
  subProcessAmount: string;
  totalProcessAmount: string;
  processDuration: string;
  etaskProcessStatus: string;
  executeTaskUser: string;
  userComment: string;
  protocolMainResults: ProtocolMainResult[];
}


export interface ProcessUI {
  processIcon: string;
  process: Process;
  processActivated: boolean;
  processIsShowActivated: boolean;
  processStatusIcon: string;
  isProcessFinish: boolean;
}

export interface Process {

  id: number;
  processEvent: string;
  processTyp: string;
  processClassification: string;
  processPlausibility: string;
  processName: string;
  processText: string;
  hasDependedProcess: boolean;
  dependedProcessIds: string[];
  scopeList: string[];
  selectedScopeList: string[];
}

export interface ExecuteProcess {

  processEvent: string;
  processTyp: string;
  processClassification: string;
  processPlausibility: string;
  processName: string;
  processText: string;
  hasDependedProcess: boolean;
  isDependedProcess: boolean;
  dependedProcessIds: string;
  context: string;
}

export interface ExecuteProcessUI {
  signature: string;
  executeMainProcesses: ExecuteMainProcess[];
  mainProcessAmount: number;
  processDuration: string;
  isProcessFinish: boolean;
  isProcessRunning: boolean;
}

export interface ExecuteMainProcess {
  signature: string;
  theme: string;
  processEvent: string;
  processTyp: string;
  processClassification: string;
  processPlausibility: string;
  processName: string;
  processText: string;
  mainProcessAmount: string;
  processList: ExecuteSubProcess[];
  time: string;
  isProcessFinish: boolean;
  processStatusIcon: string;
  taskSignature: string;
}

export interface ExecuteSubProcess {
  signature: string;
  theme: string;
  processEvent: string;
  processTyp: string;
  processClassification: string;
  processPlausibility: string;
  processName: string;
  processText: string;
  time: string;
  isProcessFinish: boolean;
  processStatusIcon: string;
  taskSignature: string;
}

export interface UserComment {
  userComment: string;
  taskSignature: string;
}
