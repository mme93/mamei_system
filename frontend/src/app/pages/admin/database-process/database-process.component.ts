import {Component, OnInit} from '@angular/core';

export interface DatabaseProcess {
  id: number;
  processLabel: string;
  processIcon: string;
  processDescription: string;
  processActivated: boolean;
  processStatusIcon: string;
}

@Component({
  selector: 'app-database-process',
  templateUrl: './database-process.component.html',
  styleUrls: ['./database-process.component.scss']
})
export class DatabaseProcessComponent implements OnInit {

  incr = 1;
  processStatusIcon = ['play_circle_filled', 'build', 'done', 'error'];

  processList: DatabaseProcess[] = [
    {
      id: 1,
      processLabel: 'Delete Database',
      processIcon: 'info',
      processDescription: 'Delete Database with all tables and data.',
      processActivated: false,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 2,
      processLabel: 'Build Table',
      processIcon: 'info',
      processDescription: 'Delete Database with all tables and data.',
      processActivated: false,
      processStatusIcon: this.processStatusIcon[0]
    }, {
      id: 3,
      processLabel: 'Set Default Dataset',
      processIcon: 'info',
      processDescription: 'Create default data in different tables, which needed for the system.',
      processActivated: false,
      processStatusIcon: this.processStatusIcon[0]
    }
  ]
  progress = 0;
  isloading = false;

  ngOnInit(): void {
  }

  startProcess(): void {
    this.progress += 50;
    this.isloading = true;
  }

}
