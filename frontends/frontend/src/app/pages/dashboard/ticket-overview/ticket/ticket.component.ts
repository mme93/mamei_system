import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ticket } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.scss'
})
export class TicketComponent implements OnInit {
  loading: boolean = true;
  ticket: Ticket | null = null;
  constructor(private route: ActivatedRoute, private ticketService: TicketService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {

      this.ticketService.getTicketById(Number(params['id'])).subscribe(result => {
        this.ticket = result;
        this.loading = false;
      },
        (error: HttpErrorResponse) => {
          console.log('HTTP Status:', error.status);
          this.loading = false;
        });
      console.log('Erhaltene ID:', params['id']);
    });
  }

}

