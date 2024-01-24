import {NgModule} from '@angular/core';
import {AdminRoutingModule} from "./admin-routing.module";
import {MatIconModule} from "@angular/material/icon";
import {CommonModule} from "@angular/common";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { ProcessComponent } from './process/process.component';
import { SystemUserComponent } from './system-user/system-user.component';


@NgModule({
  imports: [
    AdminRoutingModule,
    MatIconModule,
    CommonModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatProgressBarModule
  ],
  exports: [
    SystemUserComponent,
    ProcessComponent
  ],
  declarations: [
    ProcessComponent,
    SystemUserComponent
  ]
})
export class AdminModule {
}
