import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Process} from "../../../../../shared/services/admin/process/process.service";

@Component({
  selector: 'app-scope-dialog',
  templateUrl: './scope-dialog.component.html',
  styleUrls: ['./scope-dialog.component.scss']
})
export class ScopeDialogComponent {

  scopes:string[]=[];
  copyScopes:string[]=[];
  process:Process;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.scopes=data.process.scopeList;
    this.copyScopes=data.process.scopeList;
    this.process=data.process;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.scopes = this.copyScopes.filter(item => {
        return item.toLowerCase().includes(filterValue.toLowerCase());
      }
    );
  }

}
