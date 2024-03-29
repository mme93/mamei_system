import {Component, OnInit} from '@angular/core';

import {MatCheckboxChange} from "@angular/material/checkbox";
import {ProcessService} from "../../../shared/services/admin/process/process.service";
import {MatDialog} from "@angular/material/dialog";
import {ScopeDialogComponent} from "./dialoag/scope-dialog/scope-dialog.component";
import {Process} from "../../../shared/model/admin/process/ProcessApiEntity";
import {StepperProcessUI} from "../../../shared/model/admin/process/ProcessUIElements";
import {ProcessStepperService} from "../../../shared/services/admin/process/ui/process-stepper.service";

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss']
})
export class ProcessComponent implements OnInit {

  stepperProcessUI: StepperProcessUI = this.processStepperUiService.createProcessStepperUI();

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
    this.databaseProcessService.loadProtocols(this.stepperProcessUI.secondStepProcessUI.executeTaskSignature).subscribe(
      (value) => this.stepperProcessUI.lastStepProcessUI.protocolResultUI = value);
  }

  async startProcess() {
    this.stepperProcessUI.secondStepProcessUI.isLoading = true;
    this.stepperProcessUI.secondStepProcessUI.executeProcessUI.isProcessRunning = true;
    this.stepperProcessUI.processStepText = 'Process ' + this.stepperProcessUI.secondStepProcessUI.incr + '/' + this.stepperProcessUI.secondStepProcessUI.executeProcessUI.mainProcessAmount + ' finished.'
    this.stepperProcessUI.secondStepProcessUI.incr++;

    try {
      await this.databaseProcessService.createTaskProcessProtocol(this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature);
    } catch (error) {
      console.log(error)
    }
    this.stepperProcessUI.secondStepProcessUI.executeTaskSignature = this.stepperProcessUI.secondStepProcessUI.executeProcessUI.signature;
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
    this.stepperProcessUI.secondStepProcessUI.isLoading = false;
    this.stepperProcessUI.secondStepProcessUI.isProcessFinish = true;
    this.stepperProcessUI.canDisplay = true;
  }


  changeIcon(microServiceName: string, $event: MatCheckboxChange) {
    this.stepperProcessUI=this.processStepperUiService.changeIcon(microServiceName,$event,this.stepperProcessUI);
     }

  setProcesses() {
    this.stepperProcessUI.secondStepProcessUI.startProcessList = [];
    this.stepperProcessUI.secondStepProcessUI.startProcessList = this.stepperProcessUI.firstStepProcessUI.copyProcessList.filter(item => {
        return item.processActivated;
      }
    );
    this.stepperProcessUI.processStepText = 'Start process - Need to validate.'
    this.stepperProcessUI.secondStepProcessUI.progress = 0;
  }

  validateProcess() {
    let sortProcessLists: Process[] = [];
    this.stepperProcessUI.secondStepProcessUI.startProcessList.forEach(process => sortProcessLists.push(process.process))
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
        this.stepperProcessUI.secondStepProcessUI.isProcessRunning = true;
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
      this.stepperProcessUI.secondStepProcessUI.startProcessList.forEach(startProcess => {
        if (startProcess.process.id === process.id) {
          startProcess.process.selectedScopeList = result;
        }
      })
    });
  }

  saveUserComment() {
    this.databaseProcessService.updateComment(this.stepperProcessUI.lastStepProcessUI.userComment.userComment, this.stepperProcessUI.secondStepProcessUI.executeTaskSignature);
  }

  resetProcessSelection() {
    this.stepperProcessUI = this.processStepperUiService.resetProcessSelection(this.stepperProcessUI);
  }

  resetExecuteProcess() {
    this.stepperProcessUI = this.processStepperUiService.resetExecuteProcess(this.stepperProcessUI);
  }

  newProcess() {
    this.stepperProcessUI = this.processStepperUiService.newProcess(this.stepperProcessUI);
  }
}
