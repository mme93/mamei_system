import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./core/page/account/login/login.component";
import {AuthGuard} from "./shared/service/guard/auth.guard";
import {SystemUserComponent} from "./core/page/system-user/system-user.component";

const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () => import('./core/page/dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [AuthGuard]
  },
  {path: 'login', component: LoginComponent},
  {path: 'system/user/:name', component: SystemUserComponent},
  {path: '**', redirectTo: 'dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
