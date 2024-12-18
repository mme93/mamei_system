import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {DatabaseComponent} from "./database.component";
import {DatabaseSettingsComponent} from "./database-settings/database-settings.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'settings/:databaseName',
    component: DatabaseSettingsComponent
  },
  {
    path: 'overview',
    component: DatabaseComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DatabaseRoutingModule {
}
