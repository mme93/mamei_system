import { Injectable } from '@angular/core';
import {environment} from "../../../../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StandardComponent} from "../../../../model/dashboard/Components";

@Injectable({
  providedIn: 'root'
})
export class StandardService {
  private mammeifsmComponentStandardUrl = environment.uri + ':9000/api/mameifsm/component/standard';
  constructor(private http: HttpClient) {
  }

  getAllStandardComponents() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
   return  this.http.get<StandardComponent[]>(this.mammeifsmComponentStandardUrl, httpOptions);
  }
}
