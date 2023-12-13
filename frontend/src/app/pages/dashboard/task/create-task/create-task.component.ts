import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../../../shared/services/login.service";
import {Router} from "@angular/router";
import {AppComponent} from "../../../../app.component";
import {Time} from "@angular/common";

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent {
  isLoading = false;
  checkLogin = false;

  options = new FormGroup({
    needRange: new FormControl(true, [Validators.required]),
    needDate: new FormControl(true, [Validators.required]),
    needTime: new FormControl(true, [Validators.required]),
    isStandard: new FormControl(true, [Validators.required]),
    timeStart: new FormControl<Time | null>(null),
    timeEnd: new FormControl<Time | null>(null)
  });

  task = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(1)]),
    information: new FormControl('', [Validators.required, Validators.minLength(1)])
  });

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  dateFormClass = "date_form";


  constructor(private loginService: LoginService, private router: Router, private appComponent: AppComponent) {
    this.appComponent.isSidenavDisabled = true;
  }

  getErrorMessage() {
    if (this.task.controls.name.hasError('required')) {
      return 'You must enter a value';
    }
    return this.task.controls.name.hasError('username') ? 'Not a valid username' : '';
  }

  changeIsStandard() {
    if (this.options.controls.isStandard.value) {
      this.options.controls.needDate.setValue(true);
      this.options.controls.needTime.setValue(true);
      this.changeCSS();
    }
  }

  changeWithDate() {
    if(!this.options.controls.needDate.value){
      this.options.controls.needTime.setValue(false);
    }
    this.changeCSS();
  }

  changeWithTime() {
    if(!this.options.controls.needDate.value){
      this.options.controls.needTime.setValue(false);
    }else{
      this.changeCSS();
    }
  }

  changeIsRange() {

  }

  changeCSS() {
    if (this.options.controls.needTime.value != this.options.controls.needDate.value) {
      this.dateFormClass = "date_form_two_dif_types";
    } else {
      this.dateFormClass = "date_form";
    }
  }


  login() {
    this.isLoading = true;

  }

}
