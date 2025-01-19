import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TitleEventService } from 'src/app/shared/event/title-event.service';
import { Ticket, TicketTableElement } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrl: './ticket-overview.component.scss'
})
export class TicketOverviewComponent implements OnInit {
  ticketElements: TicketTableElement[] = []
  displayedColumns: string[] = ['position', 'id', 'status', 'label', 'classification', 'title', 'date', 'createDate', 'delete'];
  dataSource = new MatTableDataSource(this.ticketElements);

  tickets: Ticket[] = [];

  constructor(private eventService: TitleEventService, private router: Router, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.ticketService.getAllTickets().subscribe(result => {
      this.tickets = result;
      this.createTableContent(result)
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
    this.eventService.updateTitle('Ticket Overview')

  }

  viewTicket(ticket: TicketTableElement) {
    this.router.navigate([`/dashboard/ticket/${ticket?.id}`]);
  }

  createTicket() {
    this.router.navigate(['/dashboard/ticket/create']);
  }

  editTicket(ticket: TicketTableElement) {
    this.router.navigate([`/dashboard/ticket/edit/${ticket?.id}`]);
  }

  deleteTicket(ticketElement: TicketTableElement) {
    let ticket: Ticket = this.tickets.filter(ticket => ticket.id === ticketElement.id)[0];
    this.ticketService.deleteTicket(ticket).subscribe(() => {
      console.log(ticket)
      for (let i: number = 0; i < this.tickets.length; i++) {
        if (ticket.id === this.tickets[i].id) {
          this.tickets.splice(i, 1);
        }
      }
      this.createTableContent(this.tickets)
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  createTableContent(tickets: Ticket[]) {
    this.ticketElements = [];
    let index = 1;
    tickets.forEach(ticket => {
      this.ticketElements.push({
        position: index,
        id: ticket.id ? ticket.id : -1,
        title: ticket.title,
        description: ticket.description,
        startDate: ticket.startDate,
        endDate: ticket.endDate,
        createDate: ticket.createDate,
        deadLine: ticket.deadLine,
        type: ticket.type,
        label: ticket.label,
        classification: ticket.classification,
        status: ticket.status
      })
      index++;
    })
    this.dataSource.data = this.ticketElements;
  }

}
