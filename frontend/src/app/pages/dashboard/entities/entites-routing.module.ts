import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EntitiesComponent} from "./entities.component";
import {PrimaryComponent} from "./primary/primary.component";
import {SecondaryComponent} from "./secondary/secondary.component";
import {GenericComponent} from "./generic/generic.component";

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
    path: 'primary',
    component: PrimaryComponent
  },
  {
    path: 'secondary',
    component: SecondaryComponent
  },
  {
    path: 'generic',
    component: GenericComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EntitiesRoutingModule {}
