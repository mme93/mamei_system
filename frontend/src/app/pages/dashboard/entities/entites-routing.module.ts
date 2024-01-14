import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EntitiesComponent} from "./entities.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'entities',
    pathMatch: 'full'
  },
  {
    path: 'entities',
    component: EntitiesComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EntitiesRoutingModule {}
