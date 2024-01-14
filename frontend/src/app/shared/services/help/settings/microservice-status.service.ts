import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {MicroService} from "../../../model/MicroSerivce";

@Injectable({
  providedIn: 'root'
})
export class MicroserviceStatusService {

  private microServiceStatusUrl = environment.uri + ':9000/api/service_status';

  constructor(private http: HttpClient) {
  }

  getMicroServiceStatus():MicroService[]{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    // @ts-ignore
    return this.http.get<MicroService[]>(this.microServiceStatusUrl, httpOptions);
  }
}
