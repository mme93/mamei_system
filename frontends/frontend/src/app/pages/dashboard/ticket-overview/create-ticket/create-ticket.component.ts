import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TitleEventService } from 'src/app/shared/event/title-event.service';
import { CreateTicket } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrl: './create-ticket.component.scss'
})
export class CreateTicketComponent implements OnInit {

  ticketForm: FormGroup;

  ticketTypes = [
    { value: 'bug', viewValue: 'Bug' },
    { value: 'feature', viewValue: 'Feature Request' },
    { value: 'support', viewValue: 'Support' }
  ];

  ticketPrio = [
    { value: 'high', viewValue: 'High' },
    { value: 'middle', viewValue: 'Middle' },
    { value: 'low', viewValue: 'Low' }
  ];

  creationDate: Date = new Date();

  constructor(private eventService: TitleEventService, private fb: FormBuilder, private ticketService: TicketService, private router: Router) {
    this.ticketForm = this.fb.group({
      title: ['Test title', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      ticketType: ['bug', [Validators.required]],
      ticketPrios: ['low', [Validators.required]],
      description: ['Test description', [Validators.required, Validators.minLength(1), Validators.maxLength(500)]],
      startDate: [this.creationDate],
      endDate: [this.creationDate],
      deadLine: [true]
    });
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Create new Ticket');
  }

  createTicket() {
    const ticket = {
      title: this.ticketForm.get('title')?.value,
      ticketType: this.ticketForm.get('ticketType')?.value,
      ticketPrios: this.ticketForm.get('ticketPrios')?.value,
      description: this.ticketForm.get('description')?.value,
      startDate: this.ticketForm.get('startDate')?.value,
      endDate: this.ticketForm.get('endDate')?.value,
      deadLine: this.ticketForm.get('deadLine')?.value
    };
    this.ticketService.createTicket(ticket).subscribe(result => {
      if (result.id) {
        this.router.navigate(['/dashboard/ticket', result.id]);
      }
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
  }
}
