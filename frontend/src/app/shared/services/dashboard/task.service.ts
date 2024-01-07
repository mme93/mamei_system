import {Injectable} from '@angular/core';
import {StandardTask} from "../../model/Task";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private dashboardCreateTaskUrl = environment.uri + ':9000/api/dashboard/create';
  private dashboardPingUrl = environment.uri + ':9000/api/dashboard/ping';

  constructor(private http: HttpClient) {
  }

  createTask(task: StandardTask) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.post(this.dashboardCreateTaskUrl, task, httpOptions);
  }

  doTask() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'text'  // Deaktiviere das automatische JSON-Parsing
    };
    // @ts-ignore
    this.http.get(this.dashboardPingUrl, httpOptions).subscribe(value => console.log(value));
  }
  test(task:StandardTask){

    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'text'
    };
    // @ts-ignore
    this.http.post(this.dashboardCreateTaskUrl, task, httpOptions).subscribe(value => console.log(value));
  }
}
