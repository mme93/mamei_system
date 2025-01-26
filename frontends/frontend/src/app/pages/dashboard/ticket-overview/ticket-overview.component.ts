import { Overlay, OverlayRef } from '@angular/cdk/overlay';
import { TemplatePortal } from '@angular/cdk/portal';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { tick } from '@angular/core/testing';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TitleEventService } from 'src/app/shared/event/title-event.service';
import { Ticket, TicketTableElement } from 'src/app/shared/model/dashboard/Ticket';
import { TicketTableFilter, TicketTableSettings, X } from 'src/app/shared/model/settings/TicketSettings';
import { TicketTableFilterService } from 'src/app/shared/services/dashboard/ticket/ticket-table-filter.service';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';
import { DialogService } from 'src/app/shared/services/dialog/dialog.service';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrl: './ticket-overview.component.scss'
})
export class TicketOverviewComponent implements OnInit {
  ticketTableSettings: TicketTableSettings = {
    defaultCoulmns: ['position', 'id', 'project_id', 'project', 'status', 'label', 'classification', 'title', 'date', 'createDate', 'buttons'],
    selectedFilter: {
      name: 'default',
      statusFilter: {
        isCREATED: true,
        isWAITING: true,
        isREFINEMENT: true,
        isIN_PROGRESS: true,
        isDONE: true
      },
      displayedColumns: ['position', 'id', 'project_id', 'project', 'status', 'label', 'classification', 'title', 'date', 'createDate', 'buttons']
    },
    filter: [{
      name: 'default',
      statusFilter: {
        isCREATED: true,
        isWAITING: true,
        isREFINEMENT: true,
        isIN_PROGRESS: true,
        isDONE: true
      },
      displayedColumns: ['position', 'id', 'project_id', 'project', 'status', 'label', 'classification', 'title', 'date', 'createDate', 'buttons']
    }, {
      name: 'Done',
      statusFilter: {
        isCREATED: false,
        isWAITING: false,
        isREFINEMENT: false,
        isIN_PROGRESS: false,
        isDONE: true
      },
      displayedColumns: ['position', 'id', 'status', 'label', 'classification', 'title', 'buttons']
    }]
  }
  filter: X = {
    filterName: '',
    done:true,
    create:true,
    in_PROGRESS:true,
    refinement:true,
    waiting:true,
    displayedColumns: []
  };
  ticketElements: TicketTableElement[] = []
  dataSource = new MatTableDataSource(this.ticketElements);

  tickets: Ticket[] = [];
  filteredTickets: Ticket[] = [];

  constructor(private dialogService: DialogService, private eventService: TitleEventService, private router: Router,
    private ticketService: TicketService, private ticketFilterService: TicketTableFilterService) { }

  ngOnInit(): void {
    this.ticketService.getAllTickets().subscribe(tickets => {
      this.tickets = tickets;
      this.ticketFilterService.getFilterById(1).subscribe((filter: X) => {
        this.filter = filter;
        this.filterTable(tickets, filter);
        this.createTableContent(this.filteredTickets);
      },
        (error: HttpErrorResponse) => {
          console.log('HTTP Status:', error.status);
        });

    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
    this.eventService.updateTitle('Ticket Overview')

  }

  filterTable(response: Ticket[], filter: X) {
    const tickets = response;
    this.filteredTickets = []
    this.filteredTickets = tickets.filter(ticket => {
      const isOkay= (ticket.status === 'CREATED' &&  filter.create)||
      (ticket.status === 'IN_PROGRESS' &&  filter.in_PROGRESS)||
      (ticket.status === 'REFINEMENT' &&  filter.refinement)||
      (ticket.status === 'WAITING' &&  filter.waiting)||
      (ticket.status === 'DONE' &&  filter.done);
      return isOkay;
    });
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
        projectId: ticket.projectId,
        projectLabel: ticket.projectLabel,
        startDate: ticket.startDate,
        endDate: ticket.endDate,
        createDate: ticket.createDate,
        deadLine: ticket.deadLine,
        type: ticket.type,
        label: ticket.label,
        classification: ticket.classification,
        status: ticket.status,
      })
      index++;
    })
    this.dataSource.data = this.ticketElements;
  }

  openSettingsDialog(): void {
    this.dialogService.openTicketSettingsDialog(this.ticketTableSettings).subscribe((result: TicketTableSettings) => {
      if (result) {
        this.ticketTableSettings = result;
      }
    });
  }

}
