import {Component} from '@angular/core';
import {TaskService} from "../../../../shared/services/dashboard/task.service";
import {StandardTask} from "../../../../shared/model/Task";
import {MatTableDataSource} from "@angular/material/table";

export interface TaskRowElement {
  position: number;
  name: string;
  information: string;
  date: string;
  symbol: string;
}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'app-task-overview',
  templateUrl: './task-overview.component.html',
  styleUrls: ['./task-overview.component.scss']
})
export class TaskOverviewComponent {
  taskRow: TaskRowElement[] = [];
  //displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  displayedColumns: string[] = ['position','name', 'information', 'date', 'symbol'];
  dataSource = new MatTableDataSource(this.taskRow);
  task: StandardTask[] = [];

  constructor(private taskService: TaskService) {
    taskService.getAllTask().subscribe(value => this.setData(value));
  }

  setData(task: StandardTask[]) {
    for (let i = 1; i < task.length + 1; i++) {
      this.taskRow.push({
        position: i,
        name: task[i-1].name,
        information: task[i-1].information,
        date: task[i-1].startDate+' '+task[i-1].startTime+'   -   '+task[i-1].endDate+' '+task[i-1].endTime,
        symbol: 'auto'
      });
    }
    this.dataSource = new MatTableDataSource(this.taskRow);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
