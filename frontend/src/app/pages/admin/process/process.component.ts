import {Component, OnInit} from '@angular/core';
import {
  DatabaseProcess,
  DatabaseProcessUI
} from "../../../shared/services/admin/process/process.service";
import {MatCheckboxChange} from "@angular/material/checkbox";
import {ProcessService} from "../../../shared/services/admin/process/process.service";

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss']
})
export class ProcessComponent implements OnInit {
  default = 'Choose your steps for Database Processes';
  incr = 0;
  processStatusIcon = ['play_disabled', 'play_arrow', 'build', 'done', 'error'];
  processList: DatabaseProcessUI[] = [];
  copyProcessList: DatabaseProcessUI[] = [];
  currentProcess = 1;
  progress = 0;
  isLoading = false;
  itemText = this.default;

  constructor(private databaseProcessService: ProcessService) {
  }

  ngOnInit(): void {
    this.databaseProcessService.getProcesses().subscribe((process: DatabaseProcess[]) => {
      this.processList = this.databaseProcessService.getDataBaseProcess(process);
      this.copyProcessList = this.databaseProcessService.getDataBaseProcess(process)
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.processList = this.copyProcessList.filter(item => {
        return item.process.processText.toLowerCase().includes(filterValue.toLowerCase());
      }
    );
  }

  async startProcess() {
    this.isLoading = true;
    const selectedProcess = this.processList.filter(process => {
      process.processIsShowActivated = process.processActivated;
      return process.processActivated;
    });
    this.itemText = 'Process ' + this.incr + '/' + selectedProcess.length + ' finished.'
    this.incr++;
    this.isLoading = true;
    for (const process of this.processList) {
      if (process.processActivated) {
        process.processStatusIcon = this.processStatusIcon[2];
        try {
          const result = await this.databaseProcessService.startProcess(process);
          console.log(result);
          this.itemText = 'Process ' + this.incr + '/' + selectedProcess.length + ' finished.'
          process.processStatusIcon = this.processStatusIcon[3];
          process.isProcessFinish = true;
          this.progress = Number((this.progress + (100 / selectedProcess.length)).toFixed(2));
        } catch (error) {
          console.error(error);
        }
        this.incr++;
      }
    }
    this.progress = 100;
    this.incr = 0;
  }

  changeIcon(index: number, $event: MatCheckboxChange) {

    if ($event.checked) {
      this.copyProcessList[index].processActivated=this.processList[index].processActivated;
      this.processList[index].processStatusIcon = this.processStatusIcon[1];
    } else {
      this.processList[index].processStatusIcon = this.processStatusIcon[0];
    }
    this.processList[index].processActivated = $event.checked;
  }
}
