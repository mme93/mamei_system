import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../shared/services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  isLoading = false;
  user = new FormGroup({
    username: new FormControl('admin', [Validators.required, Validators.minLength(1)]),
    password: new FormControl('123', [Validators.required, Validators.minLength(1)])
  });

  constructor(private loginService: LoginService, private router: Router) {
    localStorage.setItem('login', 'false')
    window.parent.postMessage({ type: 'updateLogoutButton', value: false }, '*');
  }

  getErrorMessage() {
    if (this.user.controls.username.hasError('required')) {
      return 'You must enter a value';
    }
    return this.user.controls.username.hasError('username') ? 'Not a valid username' : '';
  }

  getPasswordErrorMessage() {
    if (this.user.controls.password.hasError('required')) {
      return 'You must enter a value';
    }
    return this.user.controls.password.hasError('password') ? 'Not a valid password' : '';
  }

  login() {
    this.isLoading = true;
    // @ts-ignore
    if (this.loginService.login(this.user.controls.username.value, this.user.controls.password.value)) {
      this.isLoading = false;
      window.parent.postMessage({ type: 'updateLogoutButton', value: true }, '*');
      this.router.navigate(['/home'])
    } else {
      this.isLoading = false;
    }
  }
}
