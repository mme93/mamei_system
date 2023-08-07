import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JwtAuthenticationResponse, LoginRequest} from "../model/Login";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:9000/authenticate/login';

  constructor(private http: HttpClient) {
  }


  login(request: LoginRequest): Observable<JwtAuthenticationResponse> {
    const url = `${this.apiUrl}`;
    localStorage.setItem('login', 'true')
    return this.http.post<JwtAuthenticationResponse>(url, request);
  }

}
