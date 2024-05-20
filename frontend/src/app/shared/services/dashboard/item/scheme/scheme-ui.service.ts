import {Injectable} from '@angular/core';
import {ComponentTableRow, StandardComponent} from "../../../../model/dashboard/Components";
import {ComponentSettingDialogComponent} from "../../../../../pages/dashboard/dialog/component-setting-dialog/component-setting-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Injectable({
  providedIn: 'root'
})
export class SchemeUiService {

  constructor(private dialog: MatDialog) {
  }

  removeComponent(i: number, componentTableRow: ComponentTableRow[]) {
    componentTableRow.splice(i, 1);
    return this.updatePosition(componentTableRow);
  }

  moveComponentUp(i: number, componentTableRow: ComponentTableRow[]) {
    let temp = componentTableRow[i];
    componentTableRow[i] = componentTableRow[i - 1];
    componentTableRow[i - 1] = temp;
    return this.updatePosition(componentTableRow);
  }

  moveComponentDown(i: number, componentTableRow: ComponentTableRow[]) {
    let temp = componentTableRow[i];
    componentTableRow[i] = componentTableRow[i + 1];
    componentTableRow[i + 1] = temp;
    return this.updatePosition(componentTableRow);
  }

  updatePosition(componentTableRow: ComponentTableRow[]) {
    for (let i = 0; i < componentTableRow.length; i++) {
      componentTableRow[i].position = i + 1;
    }
    return componentTableRow;
  }


}
