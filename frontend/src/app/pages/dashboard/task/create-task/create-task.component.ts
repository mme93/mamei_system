import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent {

  constructor(private fb: FormBuilder) {}

  taskForms: FormGroup =this.fb.group({
    enableDates: [false],
    title: ['', Validators.required],
    info: [''],
    startDate: [''],
    endDate: [''],
  });

  taskForm = new FormGroup({
    name: new FormControl( '',[Validators.required, Validators.minLength(1)]),
    information: new FormControl( '',[Validators.required, Validators.minLength(1)])
  });

  ngOnInit(): void {
  }

  submitForm() {
    console.log(this.taskForm.value);
  }
}
