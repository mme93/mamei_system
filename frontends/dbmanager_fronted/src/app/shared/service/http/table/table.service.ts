import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../../enviroments/environment";
import {DatabaseTableView} from "../../../model/table/TableView";

@Injectable({
  providedIn: 'root'
})
export class TableService {

  constructor(private http: HttpClient) {
  }

  getAllTableNames(serverName: string | null,databaseName: string | null):Observable<string[]>{
    const url = environment.uri + 'table/' + serverName + '/'+databaseName+"/name/all";
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<string[]>(url, httpOptions);
  }

  getTableByNameAndDatabase(serverName: string | null,databaseName: string | null,tableName: string | null){
    const url = environment.uri + 'table/' + serverName + '/'+databaseName+"/"+tableName;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<DatabaseTableView>(url, httpOptions);
  }
}
