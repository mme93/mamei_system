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
  },
  {
    path: 'entities',
    loadChildren: () => import('./entities/entities.module').then(m => m.EntitiesModule),
    canActivate: [SecurityGuard]
  },
  {
    path: 'item',
    loadChildren: () => import('./item/item.module').then(m => m.ItemModule),
    canActivate: [SecurityGuard]
  },
  {
    path: 'ticket',
    loadChildren: () => import('./ticket-overview/ticket-overview.module').then(m => m.TicketOverviewModule),
    canActivate: [SecurityGuard]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
