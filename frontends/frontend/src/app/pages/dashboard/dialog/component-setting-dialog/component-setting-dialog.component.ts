import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-component-setting-dialog',
  templateUrl: './component-setting-dialog.component.html',
  styleUrls: ['./component-setting-dialog.component.scss']
})
export class ComponentSettingDialogComponent {
  inputValue: string | undefined;


  constructor(@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<ComponentSettingDialogComponent>) {
  }

  removeContent(removeContent:string) {
    this.data.tableData.standardComponentSettings.subContent=
      this.data.tableData.standardComponentSettings.subContent.filter( (item: string) => item !== removeContent);
  }

  addContent() {
    this.data.tableData.standardComponentSettings.subContent.push(this.inputValue);
    this.inputValue='';
  }
}
