import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./dashboard.component";
import {NgModule} from "@angular/core";
import {AuthGuard} from "../../../shared/service/guard/auth.guard";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: DashboardComponent
  },
  {
    path: 'server',
    loadChildren: () => import('./server/server.module').then(m => m.ServerModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'table',
    loadChildren: () => import('./table/table.module').then(m => m.TableModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'database',
    loadChildren: () => import('./database/database.module').then(m => m.DatabaseModule),
    canActivate: [AuthGuard]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {
}
