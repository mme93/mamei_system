import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";


export interface AccountInfo {
  id: number;

  userId: number;

  firstName: String;

  lastName: String;

  username: String;

  callNumber: String;

  email: String;

  role: String;

  microServices: String;
}

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountInfoUrl = environment.uri + ':9000/api/user/account';

  constructor(private http: HttpClient) {
  }

  loadAccount() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<AccountInfo>(this.accountInfoUrl, httpOptions);
  }

}
