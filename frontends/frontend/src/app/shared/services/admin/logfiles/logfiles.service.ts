import { Injectable } from '@angular/core';
import {environment} from "../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Process} from "../../../model/admin/process/ProcessApiEntity";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LogfilesService {

  private logInfoFileDownloadUrl = environment.uri + ':9000/api/massdatapool/log/info'

  constructor(private http: HttpClient) {
  }

  downloadInfoLog(): Observable<Blob> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      }),
      responseType: 'blob' as 'json' // Important
    };
    return this.http.get<Blob>(this.logInfoFileDownloadUrl, httpOptions);
  }
}
