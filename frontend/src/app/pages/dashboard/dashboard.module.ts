import { NgModule } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {DashboardRoutingModule} from "./dashboard-routing.module";
import {DashboardComponent} from "./dashboard.component";
import {OverviewComponent} from "./overview/overview.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatNativeDateModule} from "@angular/material/core";
import {MatTableModule} from "@angular/material/table";
import { UtilsComponent } from './utils/utils.component';
import {TaskComponent} from "./utils/task/task.component";
import {TaskOverviewComponent} from "./utils/task/task-overview/task-overview.component";
import {EditTaskComponent} from "./utils/task/edit-task/edit-task.component";
import {CreateTaskComponent} from "./utils/task/create-task/create-task.component";
import {FlexLayoutModule} from "@angular/flex-layout";
import { EntitiesComponent } from './entities/entities.component';
import {MatMenuModule} from "@angular/material/menu";
import { ItemComponent } from './item/item.component';
import { CreateItemComponent } from './item/create-item/create-item.component';
import { EditItemComponent } from './item/edit-item/edit-item.component';


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
    MatProgressBarModule,
    MatNativeDateModule,
    MatTableModule,
    FlexLayoutModule,
    MatMenuModule
  ],
  exports: [
    UtilsComponent
  ],
  declarations: [DashboardComponent, TaskComponent, OverviewComponent, CreateTaskComponent, TaskOverviewComponent, EditTaskComponent, UtilsComponent, EntitiesComponent, ItemComponent, CreateItemComponent, EditItemComponent]
})
export class DashboardModule {}
