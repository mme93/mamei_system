import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JwtAuthenticationRequest, JwtAuthenticationResponse, LoginRequest} from "../model/Login";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiLoginUrl = environment.uri+':9000/api/auth/login';
  private apiIsTokenExpired = environment.uri+':9000/api/auth/isTokenExpired';

  constructor(private http: HttpClient) {
  }
  isTokenExpired(){
    const token= {token: localStorage.getItem('token')}
    return this.http.post<JwtAuthenticationResponse>(this.apiIsTokenExpired,token);
  }

  login(request: LoginRequest): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiLoginUrl, request);
  }

}
