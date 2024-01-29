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

@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  private databaseProcessStartUrl = environment.uri + ':9000/api/process/newJob';
  private databaseProcessSortUrl = environment.uri + ':9000/api/process/sort';
  private databaseProcessesUrl = environment.uri + ':9000/api/process/'

  constructor(private http: HttpClient) {
  }

  startProcess(process: ProcessUI): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'text'
    };
    let executeProcess: ExecuteProcess = {
      processEvent: process.process.processEvent,
      processTyp: process.process.processTyp,
      processClassification: process.process.processClassification,
      processPlausibility: process.process.processPlausibility,
      processName: process.process.processName,
      processText: process.process.processText,
      hasDependedProcess: process.process.hasDependedProcess,
      isDependedProcess: false,
      dependedProcessIds: process.process.dependedProcessIds.toString(),
      context: 'APIGATEWAY'
    }
    // @ts-ignore
    return this.http.post(this.databaseProcessStartUrl, executeProcess, httpOptions).toPromise();
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
    return this.http.put<Process[]>(this.databaseProcessSortUrl, startProcessList, httpOptions);
  }
}
