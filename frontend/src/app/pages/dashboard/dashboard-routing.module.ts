import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OverviewComponent} from "./overview/overview.component";
import {DashboardComponent} from "./dashboard.component";
import {SecurityGuard} from "../../shared/guard/security.guard";
import {TaskComponent} from "./utils/task/task.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'board',
    pathMatch: 'full'
  },
  {
    path: 'board',
    component: DashboardComponent
  },
  {
    path: 'overview',
    component: OverviewComponent
  },
  {
    path: 'task',
    component: TaskComponent
  },
  {
    path: 'utils',
    loadChildren: () => import('./utils/utils.module').then(m => m.UtilsModule),
    canActivate: [SecurityGuard]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
