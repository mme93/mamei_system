import { Component } from '@angular/core';
import {TaskService} from "../../../../shared/services/dashboard/task.service";
import {StandardTask} from "../../../../shared/model/Task";

@Component({
  selector: 'app-task-overview',
  templateUrl: './task-overview.component.html',
  styleUrls: ['./task-overview.component.scss']
})
export class TaskOverviewComponent {

   task:StandardTask[]=[];

  constructor(private taskService:TaskService) {
    taskService.getAllTask().subscribe(value => this.task=value);
  }
}
