import {DashboardRoutingModule} from "./dashboard-routing.module";
import {NgModule} from "@angular/core";
import {DashboardComponent} from "./dashboard.component";
import {ServerOverviewComponent} from './server/server-overview/server-overview.component';
import {DatabaseComponent} from './database/database.component';
import {TableComponent} from './table/table.component';
import {CardModule} from "primeng/card";
import {CommonModule} from "@angular/common";
import {SplitterModule} from "primeng/splitter";
import {TreeModule} from "primeng/tree";
import {DividerModule} from "primeng/divider";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {TableModule} from "primeng/table";
import {SplitButtonModule} from "primeng/splitbutton";
import {DropdownModule} from "primeng/dropdown";
import {PaginatorModule} from "primeng/paginator";
import {DialogService} from "primeng/dynamicdialog";
import {CheckboxModule} from "primeng/checkbox";

@NgModule({
  imports: [
    DashboardRoutingModule,
    CardModule,
    CommonModule,
    SplitterModule,
    TreeModule,
    DividerModule,
    ButtonModule,
    InputTextModule,
    DividerModule,
    TableModule,
    SplitButtonModule,
    DropdownModule,
    PaginatorModule,
    CheckboxModule
  ],
  providers: [
    DialogService
  ],
  exports: [],
  declarations: [DashboardComponent, ServerOverviewComponent, DatabaseComponent, TableComponent]
})
export class DashboardModule {
}


