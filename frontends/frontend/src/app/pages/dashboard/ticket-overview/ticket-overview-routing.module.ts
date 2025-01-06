
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TicketOverviewComponent } from './ticket-overview.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: TicketOverviewComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TicketOverviewRoutingModule {}

