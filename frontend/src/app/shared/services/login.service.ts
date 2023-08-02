import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() {
  }

  login(username:string, password:string) {
    if(username === 'admin' && password==='123'){
      localStorage.setItem('login', 'true')
      return true;
    }else{
      return false;
    }

  }

}
