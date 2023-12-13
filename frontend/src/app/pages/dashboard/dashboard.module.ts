import { NgModule } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {DashboardRoutingModule} from "./dashboard-routing.module";
import {DashboardComponent} from "./dashboard.component";
import {TaskComponent} from "./task/task.component";
import {OverviewComponent} from "./overview/overview.component";
import { CreateTaskComponent } from './task/create-task/create-task.component';
import { TaskOverviewComponent } from './task/task-overview/task-overview.component';
import { EditTaskComponent } from './task/edit-task/edit-task.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatProgressBarModule} from "@angular/material/progress-bar";


@NgModule({
  imports: [
    DashboardRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule,
    MatCheckboxModule,
    MatInputModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatProgressBarModule
  ],
  declarations: [DashboardComponent,TaskComponent,OverviewComponent, CreateTaskComponent, TaskOverviewComponent, EditTaskComponent]
})
export class DashboardModule {}
