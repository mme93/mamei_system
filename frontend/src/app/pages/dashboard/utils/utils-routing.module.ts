import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PersonComponent} from "./person/person.component";
import {LocationComponent} from "./location/location.component";
import {ChecklistComponent} from "./checklist/checklist.component";
import {UtilsComponent} from "./utils.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: UtilsComponent
  },
  {
    path: 'person',
    component: PersonComponent
  },
  {
    path: 'location',
    component: LocationComponent
  },
  {
    path: 'checklist',
    component: ChecklistComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UtilsRoutingModule {}
