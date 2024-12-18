import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ShowTableComponent} from "./show-table/show-table.component";
import {TableComponent} from "./table.component";
import {CreateTableComponent} from "./create-table/create-table.component";

const routes: Routes = [
  {
    path: '',
    component: TableComponent
  },
  {
    path: 'show/:tableName/:databaseName',
    component: ShowTableComponent
  }
  ,
  {
    path: 'create/:tableName/:databaseName',
    component: CreateTableComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TableRoutingModule {
}
