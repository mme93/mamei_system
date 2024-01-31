import {Injectable} from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

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
  isProcessRunning:boolean;
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
  processStatusIcon:string;
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
  processStatusIcon:string;
}


@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  private databaseProcessTestUrl = environment.uri + ':9000/api/process/test';
  private databaseProcessStartUrl = environment.uri + ':9000/api/process/newJob';
  private databaseProcessSortUrl = environment.uri + ':9000/api/process/sort';
  private databaseProcessesUrl = environment.uri + ':9000/api/process/'
  constructor(private http: HttpClient) {
  }


  startExecuteMainProcess(process:ExecuteMainProcess): Promise<any> {
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

  startExecuteSubProcess(process:ExecuteSubProcess): Promise<any> {
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
}
