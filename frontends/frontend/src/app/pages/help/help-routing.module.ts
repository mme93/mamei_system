import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HelpComponent} from "./help.component";
import {SecurityGuard} from "../../shared/guard/security.guard";
import {InformationComponent} from "./information/information.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'board',
    pathMatch: 'full'
  },
  {
    path: 'board',
    component: HelpComponent
  },
  {
    path: 'information',
    component: InformationComponent,
    canActivate: [SecurityGuard]
  },
  {
    path: 'settings',
    loadChildren: () => import('./settings/settings.module').then(m => m.SettingsModule),
    canActivate: [SecurityGuard]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HelpRoutingModule {}
