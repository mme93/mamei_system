import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrl: './ticket-overview.component.scss'
})
export class TicketOverviewComponent implements OnInit {

  tickets: Ticket[] = [];

  constructor(private router: Router, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.ticketService.getAllTickets().subscribe(result => {
      this.tickets = result;
      console.log(this.tickets)
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
  }

  viewTicket(ticket: Ticket) {
    this.router.navigate([`/dashboard/ticket/${ticket?.id}`]);
  }

  editTicket(ticket: Ticket) {
    this.router.navigate([`/dashboard/ticket/edit/${ticket?.id}`]);
  }

  createTicket() {
    this.router.navigate(['/dashboard/ticket/create']);
  }

}
