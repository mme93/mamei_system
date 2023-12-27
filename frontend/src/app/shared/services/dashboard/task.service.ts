import { Injectable } from '@angular/core';
import {StandardTask} from "../../model/Task";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private dashboardCreateTaskUrl = environment.uri+':9000/api/dashboard/create';

  constructor(private http: HttpClient) { }

  createTask(task:StandardTask){
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      withCredentials: true
    };
    console.log(httpOptions)
    return this.http.post(this.dashboardCreateTaskUrl, null,httpOptions);
  }
}
