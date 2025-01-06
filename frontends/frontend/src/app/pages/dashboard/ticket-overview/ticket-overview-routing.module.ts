
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TicketOverviewComponent } from './ticket-overview.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { EditTicketComponent } from './edit-ticket/edit-ticket.component';
import { TicketComponent } from './ticket/ticket.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: TicketOverviewComponent
  },
  { path: 'create', component: CreateTicketComponent },
  { path: 'edit/:id', component: EditTicketComponent },
  { path: ':id', component: TicketComponent }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TicketOverviewRoutingModule { }

