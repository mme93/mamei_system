import {Component, OnInit} from '@angular/core';
import {MatCheckboxChange} from "@angular/material/checkbox";
import {DatabaseProcess, DatabaseProcessService} from "../../../shared/services/admin/database-process.service";


@Component({
  selector: 'app-database-process',
  templateUrl: './database-process.component.html',
  styleUrls: ['./database-process.component.scss']
})
export class DatabaseProcessComponent implements OnInit {
  default = 'Choose your steps for Database Processes';
  incr = 0;
  processStatusIcon = ['play_disabled', 'play_arrow', 'build', 'done', 'error'];
  processList: DatabaseProcess[] = [];
  currentProcess = 1;
  progress = 0;
  isLoading = false;
  itemText = this.default;

  constructor(private databaseProcessService: DatabaseProcessService) {
  }

  ngOnInit(): void {
    this.processList = this.databaseProcessService.getDataBaseProcess(this.processStatusIcon);
  }

  startProcess(): void {
    this.isLoading = true;
    this.processList.forEach(process => {
      process.processIsShowActivated = process.processActivated;
      if (process.processActivated) {
        process.processStatusIcon = this.processStatusIcon[2];
        this.incr++;
      }
    });

    this.itemText = 'Database Processes ' + this.currentProcess + '/' + this.incr + ' in progress.';

  }

  changeIcon(index: number, $event: MatCheckboxChange) {
    if ($event.checked) {
      this.processList[index].processStatusIcon = this.processStatusIcon[1];
    } else {
      this.processList[index].processStatusIcon = this.processStatusIcon[0];
    }
    this.processList[index].processActivated = $event.checked;
  }
}
