import {Component, OnInit} from '@angular/core';
import {
  DatabaseProcess,
  DatabaseProcessUI,
  ProcessService
} from "../../../shared/services/admin/process/process.service";
import {MatCheckboxChange} from "@angular/material/checkbox";

@Component({
  selector: 'app-system-user',
  templateUrl: './system-user.component.html',
  styleUrls: ['./system-user.component.scss']
})
export class SystemUserComponent implements OnInit {
  default = 'Choose your steps for Database Processes';
  incr = 0;
  processStatusIcon = ['play_disabled', 'play_arrow', 'build', 'done', 'error'];
  processList: DatabaseProcessUI[] = [];
  copyProcessList: DatabaseProcessUI[] = [];
  startProcessList: DatabaseProcessUI[] = [];
  currentProcess = 1;
  progress = 0;
  isLoading = false;
  itemText = this.default;
  isProcessRunning = false;
  isProcessFinish = false;

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
    for (const process of this.startProcessList) {
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
    this.progress = 100;
    this.incr = 0;
    this.isLoading = false;
    this.isProcessFinish = true;
  }

  changeIcon(index: number, $event: MatCheckboxChange) {

    if ($event.checked) {
      this.copyProcessList[index].processActivated = this.processList[index].processActivated;
      this.processList[index].processStatusIcon = this.processStatusIcon[1];
    } else {
      this.processList[index].processStatusIcon = this.processStatusIcon[0];
    }
    this.processList[index].processActivated = $event.checked;
  }

  setProcesses() {
    this.startProcessList = [];
    this.startProcessList = this.copyProcessList.filter(item => {
        return item.processActivated;
      }
    );
    this.itemText = 'Start process - Need to sort.'
  }

  validateProcess() {
    let sortProcessLists: DatabaseProcess[] = [];
    this.startProcessList.forEach(process => sortProcessLists.push(process.process))
    this.databaseProcessService.sortProcess(sortProcessLists).subscribe(value => console.log(value));
    this.isProcessRunning=true;
  }
}
