import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminComponent} from "./admin.component";
import {ProcessComponent} from "./process/process.component";
import {SystemUserComponent} from "./system-user/system-user.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'menu',
    pathMatch: 'full'
  },
  {
    path: 'menu',
    component: AdminComponent
  },
  {
    path: 'process',
    component: ProcessComponent
  },
  {
    path: 'system_user',
    component: SystemUserComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {
}
