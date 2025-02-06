import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSelectChange } from '@angular/material/select';
import { Router } from '@angular/router';
import { ticketDropDown } from 'src/app/shared/data/TicketData';
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
  systems = ticketDropDown.systems;
  types = ticketDropDown.types;
  labels = ticketDropDown.labels;
  classifications = ticketDropDown.classifications;
  projects_frontend = ticketDropDown.projects_frontend;
  projects_backend = ticketDropDown.projects_backend;
  projects = this.projects_backend;
  creationDate: Date = new Date();

  constructor(private eventService: TitleEventService, private fb: FormBuilder, private ticketService: TicketService, private router: Router) {
    this.ticketForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(500)]],
      startDate: [this.creationDate],
      endDate: [this.creationDate],
      deadLine: [true],
      type: ['TASK', [Validators.required]],
      label: ['CODE', [Validators.required]],
      classification: ['LOW', [Validators.required]],
      system: ['BACKEND', [Validators.required]],
      project: ['NOTHING_SELECTED', [Validators.required]]
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
      createDate: this.creationDate,
      deadLine: this.ticketForm.get('deadLine')?.value,
      project: this.ticketForm.get('project')?.value,
      system: this.ticketForm.get('system')?.value,
      type: this.ticketForm.get('type')?.value,
      label: this.ticketForm.get('label')?.value,
      classification: this.ticketForm.get('classification')?.value
    } as CreateTicket;

    this.ticketService.createTicket(ticket).subscribe(result => {
      if (result.id) {
        this.router.navigate(['/dashboard/ticket', result.id]);
      }
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
  }
  backToOverview() {
    this.router.navigate(['/dashboard/ticket/overview']);
  }

  onSystemChange($event: MatSelectChange) {
    this.projects = [];
    this.ticketForm.get('project')?.setValue('NOTHING_SELECTED');
    if ($event.value === this.systems[0].value) {
      this.projects = this.projects_backend;
    } else {
      this.projects = this.projects_frontend;
    }
  }
}
