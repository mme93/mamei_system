import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

export interface ScopeCheckBox{
  isSelected:boolean;
  value:string;
}

@Component({
  selector: 'app-scope-dialog',
  templateUrl: './scope-dialog.component.html',
  styleUrls: ['./scope-dialog.component.scss']
})
export class ScopeDialogComponent {

  scopeCheckbox:ScopeCheckBox[]=[];
  scopeValues:string[]=[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    data.process.scopeList.forEach((scope: any) =>{
      let wasSelected=data.process.selectedScopeList.filter((selectedScope: string) => selectedScope ===scope).length===1;
      this.scopeCheckbox.push({
        isSelected:wasSelected,
        value:scope.toString()
      })
    });
    this.scopeValues=data.process.selectedScopeList;
  }


  changeCheckbox(value:string, $event: any) {
    for (let i = 0; i < this.scopeCheckbox.length; i++) {
      if (this.scopeCheckbox[i].value === value) {
        if($event.checked){
          this.scopeValues.push(value);
        }else{
          this.scopeValues=this.scopeValues.filter(scope => scope !== value);
        }
      }
    }
  }
}
