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

  async startProcess() {
    this.isLoading = true;
    const selectedProcess= this.processList.filter(process =>{
      process.processIsShowActivated = process.processActivated;
      return process.processActivated;
    });
    this.itemText='Process '+this.incr+'/'+selectedProcess.length+' finished.'
    this.incr++;
    this.isLoading = true;
    for (const process of this.processList) {
      if (process.processActivated) {
        process.processStatusIcon = this.processStatusIcon[2];
        try {
          const result = await this.databaseProcessService.startProcess();
          console.log(result);
          this.itemText=this.default+'\n Process '+this.incr+'/'+selectedProcess.length+' finished.'
          process.processStatusIcon = this.processStatusIcon[3];
          process.isProcessFinish=true;
          this.progress=this.progress+(100/selectedProcess.length);
        } catch (error) {
          console.error(error);
        }
        this.incr++;
      }
    }
    this.incr=1;
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
