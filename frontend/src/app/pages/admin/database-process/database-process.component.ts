import {Component, OnInit} from '@angular/core';
import {MatCheckboxChange} from "@angular/material/checkbox";

export interface DatabaseProcess {
  id: number;
  processLabel: string;
  processIcon: string;
  processDescription: string;
  processActivated: boolean;
  processIsShowActivated: boolean;
  processStatusIcon: string;
}

@Component({
  selector: 'app-database-process',
  templateUrl: './database-process.component.html',
  styleUrls: ['./database-process.component.scss']
})
export class DatabaseProcessComponent implements OnInit {
  default='Choose your steps for Database Processes';
  incr = 0;
  processStatusIcon = ['play_disabled', 'play_arrow', 'build', 'done', 'error'];

  processList: DatabaseProcess[] = [
    {
      id: 1,
      processLabel: 'BackUp Database-Data',
      processIcon: 'info',
      processDescription: 'Create a BackUp for this Database.',
      processActivated: false,
      processIsShowActivated: true,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 2,
      processLabel: 'Delete Database mamei_system',
      processIcon: 'info',
      processDescription: 'Delete Database with all tables and data.',
      processActivated: false,
      processIsShowActivated: true,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 3,
      processLabel: 'Create Database mamei_system',
      processIcon: 'info',
      processDescription: 'Restart all Micro-Services, to create Tables.',
      processActivated: false,
      processIsShowActivated: true,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 4,
      processLabel: 'Restart all needed Micro-Services and create Table',
      processIcon: 'info',
      processDescription: 'Restart all Micro-Services, to create Tables.',
      processActivated: false,
      processIsShowActivated: true,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 5,
      processLabel: 'Set Default Dataset',
      processIcon: 'info',
      processDescription: 'Create default data in different tables, which needed for the system.',
      processActivated: false,
      processIsShowActivated: true,
      processStatusIcon: this.processStatusIcon[0]
    }
  ]
  currentProcess = 1;
  progress = 0;
  isLoading = false;
  itemText = this.default;

  ngOnInit(): void {
  }

  startProcess(): void {
    this.isLoading = true;
    console.log(this.processList)
    this.processList.forEach(process => {
      process.processIsShowActivated = process.processActivated;
      if (process.processActivated) {
        process.processStatusIcon=this.processStatusIcon[2];
        this.incr++;
      }
    });
    this.itemText ='Database Processes ' +this.currentProcess + '/' + this.incr+' in progress.';

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
