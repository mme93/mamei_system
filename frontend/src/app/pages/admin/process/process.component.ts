import {Component, OnInit} from '@angular/core';

import {MatCheckboxChange} from "@angular/material/checkbox";
import {ProcessService} from "../../../shared/services/admin/process/process.service";
import {MatDialog} from "@angular/material/dialog";
import {ScopeDialogComponent} from "./dialoag/scope-dialog/scope-dialog.component";
import {Process, ProcessUI} from "../../../shared/model/admin/process/ProcessApiEntity";
import {StepperProcessUI} from "../../../shared/model/admin/process/ProcessUIElements";
import {ProcessStepperService} from "../../../shared/services/admin/process/ui/process-stepper.service";

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss']
})
export class ProcessComponent implements OnInit {

  stepperProcessUI: StepperProcessUI = this.processStepperUiService.createProcessStepperUI();

  executeTaskSignature = "";
  isProcessSelected = true;
  startProcessList: ProcessUI[] = [];
  currentProcess = 1;
  isLoading = false;
  isProcessRunning = false;
  isProcessFinish = false;
  originComment = '';


  constructor(private databaseProcessService: ProcessService, public dialog: MatDialog, private processStepperUiService: ProcessStepperService) {
  }

  ngOnInit(): void {
    this.databaseProcessService.getProcesses().subscribe((process: Process[]) => {
      this.stepperProcessUI.firstStepProcessUI.processList = this.databaseProcessService.getProcess(process);
      this.stepperProcessUI.firstStepProcessUI.copyProcessList = this.databaseProcessService.getProcess(process)
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.stepperProcessUI.firstStepProcessUI.processList = this.stepperProcessUI.firstStepProcessUI.copyProcessList.filter(item => {
        return item.process.processText.toLowerCase().includes(filterValue.toLowerCase());
      }
    );
  }

  loadProtocols() {
    this.databaseProcessService.loadProtocols(this.executeTaskSignature).subscribe(
      (value) => this.stepperProcessUI.lastStepProcessUI.protocolResultUI = value);
  }

  async startProcess() {
    this.isLoading = true;
    this.stepperProcessUI.secondStepProcessUI.executeProcessUI.isProcessRunning = true;
    this.stepperProcessUI.processStepText = 'Process ' + this.stepperProcessUI.secondStepProcessUI.incr + '/' + this.stepperProcessUI.secondStepProcessUI.executeProcessUI.mainProcessAmount + ' finished.'
    this.stepperProcessUI.secondStepProcessUI.incr++;

    try {
      await this.databaseProcessService.createTaskProcessProtocol(this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature);
    } catch (error) {
      console.log(error)
    }
    this.executeTaskSignature = this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature;
    for (const process of this.stepperProcessUI.secondStepProcessUI.executeProcessUI.executeMainProcesses) {
      process.processStatusIcon = this.stepperProcessUI.processStatusIcon[2];
      try {
        process.taskSignature = this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature;
        const result = await this.databaseProcessService.startExecuteMainProcess(process);
        process.isProcessFinish = true;
        console.log(result);
      } catch (error) {
        console.error(error);
      }
      for (const subProcess of process.processList) {
        subProcess.processStatusIcon = this.stepperProcessUI.processStatusIcon[2];
        try {
          subProcess.taskSignature = this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature + "|" + process.signature;
          const result = await this.databaseProcessService.startExecuteSubProcess(subProcess);
          subProcess.isProcessFinish = true;
          console.log(result);
          subProcess.processStatusIcon = this.stepperProcessUI.processStatusIcon[3];
          subProcess.isProcessFinish = true;
        } catch (error) {
          console.error(error);
        }
      }
      this.stepperProcessUI.processStepText = 'Process ' + this.stepperProcessUI.secondStepProcessUI.incr + '/' + this.stepperProcessUI.secondStepProcessUI.executeProcessUI.mainProcessAmount + ' finished.'
      process.processStatusIcon = this.stepperProcessUI.processStatusIcon[3];
      process.isProcessFinish = true;
      this.stepperProcessUI.secondStepProcessUI.progress = Number((this.stepperProcessUI.secondStepProcessUI.progress + (100 / this.stepperProcessUI.secondStepProcessUI.executeProcessUI.mainProcessAmount)).toFixed(2));
      this.stepperProcessUI.secondStepProcessUI.incr++;
    }
    this.stepperProcessUI.secondStepProcessUI.progress = 100;
    try {
      await this.databaseProcessService.closeTaskProcessProtocol(this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature);
    } catch (error) {
      console.log(error)
    }
    this.stepperProcessUI.secondStepProcessUI.incr = 0;
    this.isLoading = false;
    this.isProcessFinish = true;
    this.stepperProcessUI.canDisplay = true;
  }


  changeIcon(microServiceName: string, $event: MatCheckboxChange) {
    for (let i = 0; i < this.stepperProcessUI.firstStepProcessUI.processList.length; i++) {
      if (this.stepperProcessUI.firstStepProcessUI.processList[i].process.processName === microServiceName) {
        if ($event.checked) {
          this.stepperProcessUI.firstStepProcessUI.processList[i].processStatusIcon = this.stepperProcessUI.processStatusIcon[1];
          this.stepperProcessUI.firstStepProcessUI.copyProcessList[i].processStatusIcon = this.stepperProcessUI.processStatusIcon[1];
        } else {
          this.stepperProcessUI.firstStepProcessUI.copyProcessList[i].processStatusIcon = this.stepperProcessUI.processStatusIcon[0];
          this.stepperProcessUI.firstStepProcessUI.processList[i].processStatusIcon = this.stepperProcessUI.processStatusIcon[0];
        }
        this.stepperProcessUI.firstStepProcessUI.copyProcessList[i].processActivated = this.stepperProcessUI.firstStepProcessUI.processList[i].processActivated;
        this.stepperProcessUI.firstStepProcessUI.processList[i].processActivated = $event.checked;
      }
    }
    this.stepperProcessUI.firstStepProcessUI.copyProcessList.forEach(process => {
      if (process.processStatusIcon === this.stepperProcessUI.processStatusIcon[0]) {
        process.processActivated = false;
      }
    });
    this.isProcessSelected = !(this.stepperProcessUI.firstStepProcessUI.copyProcessList.filter(process => process.processStatusIcon === this.stepperProcessUI.processStatusIcon[1]).length > 0);
    this.stepperProcessUI.canExecute = this.stepperProcessUI.firstStepProcessUI.copyProcessList.filter(process => process.processStatusIcon === this.stepperProcessUI.processStatusIcon[1]).length > 0;
  }

  setProcesses() {
    this.startProcessList = [];
    this.startProcessList = this.stepperProcessUI.firstStepProcessUI.copyProcessList.filter(item => {
        return item.processActivated;
      }
    );
    this.stepperProcessUI.processStepText = 'Start process - Need to validate.'
    this.stepperProcessUI.secondStepProcessUI.progress = 0;
  }

  validateProcess() {
    let sortProcessLists: Process[] = [];
    this.startProcessList.forEach(process => sortProcessLists.push(process.process))
    sortProcessLists.forEach(process => process.scopeList = process.selectedScopeList);
    this.databaseProcessService.sortProcess(sortProcessLists).subscribe(value => {
      this.stepperProcessUI.secondStepProcessUI.executeProcessUI = value
      if (value.executeMainProcesses.length === 0) {
        this.stepperProcessUI.processStepText = 'No Main-Process found by this Processes';
      } else {
        this.stepperProcessUI.secondStepProcessUI.executeProcessUI.executeMainProcesses.forEach(main => {
          main.processStatusIcon = this.stepperProcessUI.processStatusIcon[1];
          main.processList.forEach(sub => sub.processStatusIcon = this.stepperProcessUI.processStatusIcon[1])
        })
        this.isProcessRunning = true;
      }
    });
  }

  open(process: Process) {
    let dialogRef = this.dialog.open(ScopeDialogComponent, {
      height: '400px',
      width: '600px',
      data: {process: process}
    });
    dialogRef.afterClosed().subscribe((result: string[]) => {
      this.startProcessList.forEach(startProcess => {
        if (startProcess.process.id === process.id) {
          startProcess.process.selectedScopeList = result;
        }
      })
    });
  }

  saveUserComment() {
    this.databaseProcessService.updateComment(this.originComment, this.executeTaskSignature);
  }

  resetProcessSelection() {
    this.stepperProcessUI.firstStepProcessUI.processList.forEach(process => {
      process.processStatusIcon = this.stepperProcessUI.processStatusIcon[0];
      process.processActivated = false;
    })
  }

  resetExecuteProcess() {
    this.resetProcessSelection();

    this.stepperProcessUI.lastStepProcessUI.protocolResultUI = {
      id: 0,
      executeTaskDate: '',
      executeEndTaskDate: '',
      signature: '',
      mainProcessAmount: '',
      subProcessAmount: '',
      totalProcessAmount: '',
      processDuration: '',
      etaskProcessStatus: '',
      executeTaskUser: '',
      userComment: '',
      protocolMainResults: []
    };
    this.executeTaskSignature = "";
    this.isProcessSelected = true;
    this.startProcessList = [];
    this.isLoading = false;
    this.stepperProcessUI.processStepText = 'Choose your steps for Database Processes';
    this.isProcessRunning = false;
    this.isProcessFinish = false;
    this.stepperProcessUI.secondStepProcessUI.executeProcessUI = {
      signature: '',
      executeMainProcesses: [],
      mainProcessAmount: 0,
      processDuration: '',
      isProcessFinish: false,
      isProcessRunning: false
    };
    this.stepperProcessUI.canExecute = false;
    this.stepperProcessUI.canDisplay = false
    this.originComment = '';
    this.databaseProcessService.getProcesses().subscribe((process: Process[]) => {
      this.stepperProcessUI.firstStepProcessUI.processList = this.databaseProcessService.getProcess(process);
      this.stepperProcessUI.firstStepProcessUI.copyProcessList = this.databaseProcessService.getProcess(process)
    });

  }

  newProcess() {
    this.resetExecuteProcess();
  }
}
