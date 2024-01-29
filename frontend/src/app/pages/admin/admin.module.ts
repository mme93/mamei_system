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
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatStepperModule} from "@angular/material/stepper";
import {MatTooltipModule} from "@angular/material/tooltip";
import { ScopeDialogComponent } from './process/dialoag/scope-dialog/scope-dialog.component';
import {MatDialog, MatDialogModule} from "@angular/material/dialog";


@NgModule({
  imports: [
    AdminRoutingModule,
    MatIconModule,
    CommonModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatStepperModule,
    MatTooltipModule,
    MatDialogModule
  ],
  exports: [
    SystemUserComponent,
    ProcessComponent
  ],
  declarations: [
    ProcessComponent,
    SystemUserComponent,
    ScopeDialogComponent
  ]
})
export class AdminModule {
}
