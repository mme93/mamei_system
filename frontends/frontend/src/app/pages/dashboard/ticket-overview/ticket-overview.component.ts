import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrl: './ticket-overview.component.scss'
})
export class TicketOverviewComponent {

  tickets = [
    { id: 1, title: 'Bug in Login' },
    { id: 2, title: 'New Feature: Dark Mode' }
  ];

  constructor(private router: Router) { }

  viewTicket(id: number) {
    this.router.navigate([`/dashboard/ticket/${id}`]);
  }

  editTicket(id: number) {
    this.router.navigate([`/dashboard/ticket/edit/${id}`]);
  }

  createTicket() {
    this.router.navigate(['/dashboard/ticket/create']);
  }

}
