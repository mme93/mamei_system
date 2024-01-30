import {Component, OnInit} from '@angular/core';
import {
  Process,
  ProcessUI
} from "../../../shared/services/admin/process/process.service";
import {MatCheckboxChange} from "@angular/material/checkbox";
import {ProcessService} from "../../../shared/services/admin/process/process.service";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ScopeCheckBox, ScopeDialogComponent} from "./dialoag/scope-dialog/scope-dialog.component";

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss']
})
export class ProcessComponent implements OnInit {
  isProcessSelected = true;
  default = 'Choose your steps for Database Processes';
  incr = 0;
  processStatusIcon = ['play_disabled', 'play_arrow', 'build', 'done', 'error'];
  processList: ProcessUI[] = [];
  copyProcessList: ProcessUI[] = [];
  startProcessList: ProcessUI[] = [];
  currentProcess = 1;
  progress = 0;
  isLoading = false;
  itemText = this.default;
  isProcessRunning = false;
  isProcessFinish = false;

  constructor(private databaseProcessService: ProcessService,public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.databaseProcessService.getProcesses().subscribe((process: Process[]) => {
      this.processList = this.databaseProcessService.getProcess(process);
      this.copyProcessList = this.databaseProcessService.getProcess(process)
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

  changeIcon(microServiceName: string, $event: MatCheckboxChange) {
    for (let i = 0; i < this.processList.length; i++) {
      if (this.processList[i].process.processName === microServiceName) {
        if ($event.checked) {
          this.processList[i].processStatusIcon = this.processStatusIcon[1];
          this.copyProcessList[i].processStatusIcon = this.processStatusIcon[1];
        } else {
          this.copyProcessList[i].processStatusIcon = this.processStatusIcon[0];
          this.processList[i].processStatusIcon = this.processStatusIcon[1];
        }
        this.copyProcessList[i].processActivated = this.processList[i].processActivated;
        this.processList[i].processActivated = $event.checked;
      }
    }
    this.copyProcessList.forEach(process => {
      if (process.processStatusIcon === this.processStatusIcon[0]) {
        process.processActivated = false;
      }
    });
    this.isProcessSelected = !(this.copyProcessList.filter(process => process.processStatusIcon === this.processStatusIcon[1]).length > 0);
  }

  setProcesses() {
    this.startProcessList = [];
    this.startProcessList = this.copyProcessList.filter(item => {
        return item.processActivated;
      }
    );
    this.itemText = 'Start process - Need to validate.'
  }

  validateProcess() {
    let sortProcessLists: Process[] = [];
    this.startProcessList.forEach(process => sortProcessLists.push(process.process))
    this.databaseProcessService.sortProcess(sortProcessLists).subscribe(value => console.log(value));
    this.isProcessRunning = true;
  }

  open(process:Process){

    let dialogRef = this.dialog.open(ScopeDialogComponent, {
      height: '400px',
      width: '600px',
      data: { process: process}
    });
    dialogRef.afterClosed().subscribe((result:string[]) => {
      this.startProcessList.forEach(startProcess =>{
        if(startProcess.process.id === process.id){
          startProcess.process.selectedScopeList=result;
        }
      })
    });
  }

}
