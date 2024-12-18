import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../shared/services/login.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../../shared/model/Login";
import {HttpErrorResponse} from '@angular/common/http';
import {AppComponent} from "../../app.component";
import {TitleEventService} from "../../shared/event/title-event.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  isLoading = false;
  checkLogin = false;

  user = new FormGroup({
    username: new FormControl('user', [Validators.required, Validators.minLength(1)]),
    password: new FormControl('test123', [Validators.required, Validators.minLength(1)])
  });

  constructor(private loginService: LoginService, private router: Router, private appComponent: AppComponent,
              private eventService: TitleEventService
  ) {
    this.appComponent.isSidenavDisabled = false;
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Login')
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
    const loginRequest: LoginRequest = {
      username: this.user.controls.username.value as string,
      password: this.user.controls.password.value as string
    };
    this.loginService.login(loginRequest).subscribe(
      result => {
        localStorage.setItem('token', 'Bearer ' + result.token);
        this.isLoading = false;
        this.appComponent.isSidenavDisabled = true;
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        this.isLoading = false;
        this.checkLogin = true;
        console.log('HTTP Status:', error.status);
      }
    );

  }
}
