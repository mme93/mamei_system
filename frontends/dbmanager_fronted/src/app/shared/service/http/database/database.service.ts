import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../enviroments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  constructor(private http: HttpClient) {
  }

  getAllDatabaseNames(serverName: string | null):Observable<string[]>{
    const url = environment.uri + 'database/' + serverName + '/all';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<string[]>(url, httpOptions);
  }

  createDatabase(serverName: string | null, databaseName: string) {
    const url = environment.uri + 'database/' + serverName + '/create/' + databaseName;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post(url, httpOptions);
  }

  deleteDatabase(serverName: string| null, databaseName: string) {
    const url = environment.uri + 'database/' + serverName + '/delete/' + databaseName;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.delete(url, httpOptions);
  }
}
