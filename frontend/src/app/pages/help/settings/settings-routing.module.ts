import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SettingsComponent} from "./settings.component";
import {AccountComponent} from "./account/account.component";
import {UiSettingsComponent} from "./ui-settings/ui-settings.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'board',
    pathMatch: 'full'
  },
  {
    path: 'board',
    component: SettingsComponent
  },
  {
    path: 'account',
    component: AccountComponent
  },
  {
    path: 'ui_settings',
    component: UiSettingsComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SettingsRoutingModule {}
