import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../../../shared/services/login.service";
import {Router} from "@angular/router";
import {AppComponent} from "../../../../app.component";

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent {
  isLoading = false;
  checkLogin = false;

  options= new FormGroup({
    needDate: new FormControl(true, [Validators.required]),
    needTime: new FormControl(true, [Validators.required]),
    isStandard: new FormControl(true, [Validators.required])
  });

  task = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(1)]),
    information: new FormControl('', [Validators.required, Validators.minLength(1)])
  });

  constructor(private loginService: LoginService, private router: Router, private appComponent: AppComponent) {
    this.appComponent.isSidenavDisabled = true;
  }

  getErrorMessage() {
    if (this.task.controls.name.hasError('required')) {
      return 'You must enter a value';
    }
    return this.task.controls.name.hasError('username') ? 'Not a valid username' : '';
  }

  login() {
    this.isLoading = true;

  }
}
