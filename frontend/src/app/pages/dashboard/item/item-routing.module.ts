import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ItemComponent} from "./item.component";
import {CreateItemComponent} from "./create-item/create-item.component";
import {EditItemComponent} from "./edit-item/edit-item.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: ItemComponent
  },
  {
    path: 'create',
    component: CreateItemComponent
  },
  {
    path: 'edit',
    component: EditItemComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ItemRoutingModule {}
