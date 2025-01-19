import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket, TicketStatusDropDown } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.scss'
})
export class TicketComponent implements OnInit {
  statusLabels: TicketStatusDropDown[] = [];
  loading: boolean = true;
  ticket: Ticket | null = null;
  constructor(private route: ActivatedRoute, private ticketService: TicketService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {

      this.ticketService.getTicketById(Number(params['id'])).subscribe(result => {
        this.ticket = result;
        this.loading = false;
        this.setLabelText(result);
      },
        (error: HttpErrorResponse) => {
          console.log('HTTP Status:', error.status);
          this.loading = false;
        });
    });
  }

  backToOverview() {
    this.router.navigate(['/dashboard/ticket/overview']);
  }

  changeStatus(status: string) {
    if (this.ticket && this.ticket.id) {
      this.ticketService.changeTicketStatus(status, this.ticket.id).subscribe(result => {
        this.ticket = result;
        this.setLabelText(result);
      });
    }
  }

  setLabelText(ticket: Ticket) {
    this.statusLabels = [];
    if (ticket.status === 'CREATED') {
      this.statusLabels.push({
        label: 'Set to In Progress',
        code: 'IN_PROGRESS'
      });
      this.statusLabels.push({
        label: 'Set to Waiting',
        code: 'WAITING'
      });
      this.statusLabels.push({
        label: 'Set to Refinement',
        code: 'REFINEMENT'
      });
    } else if (ticket.status === 'WAITING') {
      this.statusLabels.push({
        label: 'Set to Refinement',
        code: 'REFINEMENT'
      });
      this.statusLabels.push({
        label: 'Set to In Progress',
        code: 'IN_PROGRESS'
      });
      this.statusLabels.push({
        label: 'Set to Done',
        code: 'DONE'
      });
    } else if (ticket.status === 'REFINEMENT') {
      this.statusLabels.push({
        label: 'Set to Waiting',
        code: 'WAITING'
      });
      this.statusLabels.push({
        label: 'Set to In Progress',
        code: 'IN_PROGRESS'
      });
      this.statusLabels.push({
        label: 'Set to Done',
        code: 'DONE'
      });
    } else if (ticket.status === 'IN_PROGRESS') {
      this.statusLabels.push({
        label: 'Set to Waiting',
        code: 'WAITING'
      });
      this.statusLabels.push({
        label: 'Set to Refinement',
        code: 'REFINEMENT'
      });
      this.statusLabels.push({
        label: 'Set to Done',
        code: 'DONE'
      });
    } else if (ticket.status === 'DONE') {
      this.statusLabels.push({
        label: 'Set to Waiting',
        code: 'WAITING'
      });
      this.statusLabels.push({
        label: 'Set to Refinement',
        code: 'REFINEMENT'
      });
      this.statusLabels.push({
        label: 'Set to In Progress',
        code: 'IN_PROGRESS'
      });
    }

  }

}

