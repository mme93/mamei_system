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

  types = [
    { value: 'TASK', viewValue: 'Task' },
    { value: 'BUG', viewValue: 'Bug' },
    { value: 'FEATURE', viewValue: 'Feature' }
  ];

  labels = [
    { value: 'CODE', viewValue: 'Code' },
    { value: 'NOTICE', viewValue: 'Notice' }
  ];

  classsifcations = [
    { value: 'HIGH', viewValue: 'High' },
    { value: 'MIDDLE', viewValue: 'Middle' },
    { value: 'LOW', viewValue: 'Low' }
  ];

  creationDate: Date = new Date();

  constructor(private eventService: TitleEventService, private fb: FormBuilder, private ticketService: TicketService, private router: Router) {
    this.ticketForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(2000)]],
      startDate: [this.creationDate],
      endDate: [this.creationDate],
      deadLine: [true],
      type: ['TASK', [Validators.required]],
      label: ['NOTICE', [Validators.required]],
      classification: ['LOW', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Create new Ticket');
  }

  createTicket() {
    const ticket = {
      title: this.ticketForm.get('title')?.value,
      description: this.ticketForm.get('description')?.value,
      startDate: this.ticketForm.get('startDate')?.value,
      endDate: this.ticketForm.get('endDate')?.value,
      createDate:this.creationDate,
      deadLine: this.ticketForm.get('deadLine')?.value,
      type: this.ticketForm.get('type')?.value,
      label: this.ticketForm.get('label')?.value,
      classification: this.ticketForm.get('classification')?.value,
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
  backToOverview(){
    this.router.navigate(['/dashboard/ticket/overview']);
  }
}
