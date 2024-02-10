import {Injectable} from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

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

@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  private x = environment.uri + ':9000/api/protocol/x/';
  private processProtocolUpdateCommentUrl = environment.uri + ':9000/api/protocol/comment/update';
  private processProtocolCloseUrl = environment.uri + ':9000/api/protocol/close/';
  private processProtocolCreateUrl = environment.uri + ':9000/api/protocol/create/';
  private databaseProcessStartUrl = environment.uri + ':9000/api/process/newJob';
  private databaseProcessSortUrl = environment.uri + ':9000/api/process/sort';
  private databaseProcessesUrl = environment.uri + ':9000/api/process/'
  private processesProtocolUrl = environment.uri + ':9000/api/protocol/'

  constructor(private http: HttpClient) {
  }

  getProtocol(signature: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<Process[]>(this.processesProtocolUrl + signature, httpOptions);
  }

  startExecuteMainProcess(process: ExecuteMainProcess): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'text'
    };
    // @ts-ignore
    return this.http.post(this.databaseProcessStartUrl, process, httpOptions).toPromise();
  }

  startExecuteSubProcess(process: ExecuteSubProcess): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'text'
    };
    // @ts-ignore
    return this.http.post(this.databaseProcessStartUrl, process, httpOptions).toPromise();
  }

  getProcesses() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<Process[]>(this.databaseProcessesUrl, httpOptions);
  }

  getProcess(process: Process[]) {
    let processArray: ProcessUI[] = [];
    process.forEach(process => {
      process.selectedScopeList = [];
      processArray.push({
        processIcon: 'info',
        process: process,
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: 'play_disabled',
        isProcessFinish: false
      })
    })
    return processArray;
  }


  sortProcess(startProcessList: Process[]) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<ExecuteProcessUI>(this.databaseProcessSortUrl, startProcessList, httpOptions);
  }

  createTaskProcessProtocol(signature: string): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<ExecuteProcessUI>(this.processProtocolCreateUrl + signature, null, httpOptions).toPromise();
  }

  closeTaskProcessProtocol(signature: string): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<ExecuteProcessUI>(this.processProtocolCloseUrl + signature, null, httpOptions).toPromise();
  }

  loadProtocols(executeTaskSignature: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<ProtocolResultUI>(this.x + executeTaskSignature, httpOptions);
    //return this.http.get<ProtocolResultUI>(this.x + "task_YDPoVHOw", httpOptions);
  }

  updateComment(originComment: string, executeTaskSignature: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    this.http.post(this.processProtocolUpdateCommentUrl,
      {
        userComment: originComment,
        taskSignature: executeTaskSignature
      },
      httpOptions).subscribe();
  }
}
