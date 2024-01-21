import {NgModule} from '@angular/core';
import {AdminRoutingModule} from "./admin-routing.module";
import { DatabaseProcessComponent } from './database-process/database-process.component';
import {MatIconModule} from "@angular/material/icon";
import {CommonModule} from "@angular/common";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";


@NgModule({
  imports: [
    AdminRoutingModule,
    MatIconModule,
    CommonModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDividerModule,
    MatProgressSpinnerModule
  ],
    exports: [
        DatabaseProcessComponent
    ],
  declarations: [
    DatabaseProcessComponent
  ]
})
export class AdminModule {
}
