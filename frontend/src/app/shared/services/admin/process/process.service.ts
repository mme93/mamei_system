import {Injectable} from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {
  ExecuteMainProcess,
  ExecuteProcessUI,
  ExecuteSubProcess,
  Process,
  ProcessUI, ProtocolResultUI
} from "../../../model/admin/process/ProcessApiEntity";


@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  private loadProcessProtocol = environment.uri + ':9000/api/system/process/controller/load/';
  private processProtocolUpdateCommentUrl = environment.uri + ':9000/api/system/process/protocol/comment/update';
  private processProtocolCloseUrl = environment.uri + ':9000/api/system/process/protocol/close/';
  private processProtocolCreateUrl = environment.uri + ':9000/api/system/process/protocol/create/';
  private databaseProcessStartUrl = environment.uri + ':9000/api/system/process/newJob';
  private databaseProcessSortUrl = environment.uri + ':9000/api/system/process/sort';
  private databaseProcessesUrl = environment.uri + ':9000/api/system/process'

  constructor(private http: HttpClient) {
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
    return this.http.get<ProtocolResultUI>(this.loadProcessProtocol + executeTaskSignature, httpOptions);
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
