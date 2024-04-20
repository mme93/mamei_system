import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EntitiesComponent} from "./entities.component";
import {CreateEntitiesComponent} from "./create-entities/create-entities.component";
import {EditEntitiesComponent} from "./edit-entities/edit-entities.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'menu',
    pathMatch: 'full'
  },
  {
    path: 'menu',
    component: EntitiesComponent
  },
  {
    path: 'create',
    component: CreateEntitiesComponent
  },
  {
    path: 'edit',
    component: EditEntitiesComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EntitiesRoutingModule {}
