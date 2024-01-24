import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

export interface DatabaseProcessUI {
  processIcon: string;
  process: DatabaseProcess;
  processActivated: boolean;
  processIsShowActivated: boolean;
  processStatusIcon: string;
  isProcessFinish: boolean;
}

export interface DatabaseProcess {

  id: number;
  processEvent: string;
  processTyp: string;
  processName: string;
  processText: string;
  hasDependedProcess: boolean;
  isDependedProcess: boolean;
  dependedProcessIds: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProcessService {
  private databaseProcessStartUrl = environment.uri + ':9000/api/process/newJob';

  constructor(private http: HttpClient) {
  }

  startProcess(process: DatabaseProcessUI): Promise<any> {
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

  getProcesses(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<DatabaseProcess[]>("http://localhost:9000/api/process/",httpOptions);
  }

  getDataBaseProcess(process: DatabaseProcess[]) {
    let processArray:DatabaseProcessUI[]=[];
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

}
