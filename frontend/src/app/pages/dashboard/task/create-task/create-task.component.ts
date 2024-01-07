import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AppComponent} from "../../../../app.component";
import {TaskService} from "../../../../shared/services/dashboard/task.service";
import {StandardTask} from "../../../../shared/model/Task";
import {HttpErrorResponse} from "@angular/common/http";
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
    isStandard: new FormControl(true, [Validators.required])
  });


  task = new FormGroup({
    name: new FormControl('Ich gehe einakufen', [Validators.required, Validators.minLength(1)]),
    information: new FormControl('Bei Edeka und Aldi', [Validators.required, Validators.minLength(1)]),
    timeStart: new FormControl<Time | null>(null),
    timeEnd: new FormControl<Time | null>(null),
    dateStart: new FormControl<Date | null>(new Date()),
    dateEnd: new FormControl<Date | null>(new Date())
  });

  dateFormClass = "date_form";

  constructor(private router: Router, private appComponent: AppComponent, private taskService: TaskService) {
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
    if (!this.options.controls.needDate.value) {
      this.options.controls.needTime.setValue(false);
    }
    this.changeCSS();
  }

  changeWithTime() {
    if (!this.options.controls.needDate.value) {
      this.options.controls.needTime.setValue(false);
    } else {
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


  createTask() {
    // this.taskService.doTask();
    const task: StandardTask = {
      name: this.task.controls.name.value ?? '',
      information: this.task.controls.information.value ?? '',
      startDate: this.task.controls.dateStart.value ?? new Date(),
      endDate: this.task.controls.dateEnd.value ?? new Date(),
      // @ts-ignore
      startTime: this.task.controls.timeStart.value !== null ? this.task.controls.timeStart.value : new Time(),
      // @ts-ignore
      endTime: this.task.controls.timeEnd.value !== null ? this.task.controls.timeEnd.value : new Time(),
    };
    this.taskService.test(task);
    /*
    this.isLoading = true;
    const task: StandardTask = {
      name: this.task.controls.name.value ?? '',
      information: this.task.controls.information.value ?? '',
      startDate: this.task.controls.dateStart.value ?? new Date(),
      endDate: this.task.controls.dateEnd.value ?? new Date(),
      // @ts-ignore
      startTime: this.task.controls.timeStart.value !== null ? this.task.controls.timeStart.value : new Time(),
      // @ts-ignore
      endTime: this.task.controls.timeEnd.value !== null ? this.task.controls.timeEnd.value : new Time(),
    };
    this.taskService.createTask(task).subscribe(
      result => {
        this.isLoading = false;
        console.log(result)
      },
      (error: HttpErrorResponse) => {
        this.isLoading = false;
        console.log('HTTP Status:', error.status);
      }
    );
     */

  }


}
