import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminComponent} from "./admin.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'menu',
    pathMatch: 'full'
  },
  {
    path: 'menu',
    component: AdminComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {
}
