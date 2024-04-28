import { Injectable } from '@angular/core';
import {StandardComponent, StandardComponentTable} from "../../../../model/dashboard/Components";
import {ComponentSettingDialogComponent} from "../../../../../pages/dashboard/dialog/component-setting-dialog/component-setting-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Injectable({
  providedIn: 'root'
})
export class SchemeUiService {

  constructor(private dialog: MatDialog) { }

  removeComponent(i: number,tableData:StandardComponentTable[]) {
    tableData.splice(i, 1);
    return this.updatePosition(tableData);
  }

  moveComponentUp(i: number,tableData:StandardComponentTable[]) {
    let temp = tableData[i];
    tableData[i] = tableData[i - 1];
    tableData[i - 1] = temp;
    return this.updatePosition(tableData);
  }

  moveComponentDown(i: number,tableData:StandardComponentTable[]) {
    let temp = tableData[i];
    tableData[i] = tableData[i + 1];
    tableData[i + 1] = temp;
    return this.updatePosition(tableData);
  }

  updatePosition(tableData:StandardComponentTable[]) {
    for (let i = 0; i < tableData.length; i++) {
      tableData[i].position = i + 1;
    }
    return tableData;
  }

  editComponent(tableData:StandardComponentTable){
    let dialogRef = this.dialog.open(ComponentSettingDialogComponent, {
      height: '400px',
      width: '600px',
      data: {tableData: tableData}
    });
    dialogRef.afterClosed().subscribe((result: StandardComponentTable) => {
      if (result) {
        tableData = result;
      }
    });
    return tableData;
  }

  addTableData(tableData:StandardComponentTable[],standardComponent:StandardComponent,counter:number,group:number){
    tableData.push({
      position: counter,
      standardComponent: standardComponent,
      content:'',
      group:group,
      standardComponentSettings:{
        specification:standardComponent.specificationList[0],
        styleClass:standardComponent.styleClassList[0],
        subContent:[],
        errorMsg:''
      },
    });
    return tableData;
  }

}
