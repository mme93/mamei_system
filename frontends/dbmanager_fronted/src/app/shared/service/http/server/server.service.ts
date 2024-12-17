import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../enviroments/environment";
import {DatabaseOverview} from "../../../model/server/database/Database";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  constructor(private http: HttpClient) { }

  getServerOverviewByName(serverName: string | null):Observable<DatabaseOverview[]>{
    const url=environment.uri+'server/'+serverName+'/overview';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<DatabaseOverview[]>(url,httpOptions);
  }
}
