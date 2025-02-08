import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSelectChange } from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { ticketDropDown } from 'src/app/shared/data/TicketData';
import { TitleEventService } from 'src/app/shared/event/title-event.service';
import { Ticket } from 'src/app/shared/model/dashboard/Ticket';
import { TicketService } from 'src/app/shared/services/dashboard/ticket/ticket.service';

@Component({
  selector: 'app-edit-ticket',
  templateUrl: './edit-ticket.component.html',
  styleUrl: './edit-ticket.component.scss'
})
export class EditTicketComponent implements OnInit {
  ticket: Ticket | null = null;
  ticketForm: FormGroup;
  creationDate: Date = new Date();
  systems = ticketDropDown.systems;
  types = ticketDropDown.types;
  labels = ticketDropDown.labels;
  classifications = ticketDropDown.classifications;
  projects_frontend  = ticketDropDown.projects_frontend;
  projects_backend  = ticketDropDown.projects_backend;
  projects = this.projects_backend;

  constructor(
    private eventService: TitleEventService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private ticketService: TicketService,
    private router: Router
  ) {
    this.ticketForm = this.createFormGroup();
  }

  ngOnInit(): void {
    const ticketId = this.route.snapshot.params['id'];
    if (ticketId) {
      this.loadTicket(Number(ticketId));
    }
    this.eventService.updateTitle('Edit Ticket');
  }

  private createFormGroup(): FormGroup {
    return this.fb.group({
      title: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(2000)]],
      startDate: [this.creationDate],
      endDate: [this.creationDate],
      deadLine: [true],
      type: ['TASK', [Validators.required]],
      label: ['NOTICE', [Validators.required]],
      classification: ['LOW', [Validators.required]],
      project: ['NOTHING_SELECTED', [Validators.required]],
      system: ['BACKEND', [Validators.required]]
    });
  }

  private loadTicket(id: number): void {
    this.ticketService.getTicketById(id).subscribe(
      (ticket: Ticket) => {
        if(ticket.system === 'FRONTEND'){
          this.projects =this.projects_frontend;
        }
        this.ticket = ticket;

        this.ticketForm = this.fb.group({
          title: [ticket.title, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
          description: [ticket.description, [Validators.required, Validators.minLength(1), Validators.maxLength(2000)]],
          startDate: [ticket.startDate],
          endDate: [ticket.endDate],
          deadLine: [ticket.deadLine],
          type: [ticket.title, [Validators.required]],
          label: [ticket.label, [Validators.required]],
          classification: [ticket.classification, [Validators.required]],
          project: [ticket.project, [Validators.required]],
          system: [ticket.system, [Validators.required]]
        });
        this.ticketForm.patchValue(ticket);
      },
      (error: HttpErrorResponse) => {
        console.error('Error loading ticket:', error);
      }
    );
  }

  back(): void {
    this.router.navigate(['/dashboard/ticket/overview']);
  }

  updateTicket(): void {
    if (this.ticketForm.valid && this.ticket) {
      const updatedTicket = { ...this.ticket, ...this.ticketForm.value };
      this.ticketService.updateTicket(updatedTicket).subscribe(
        () => this.back(),
        (error: HttpErrorResponse) => {
          console.error('Error updating ticket:', error);
        }
      );
    }
  }

   onSystemChange($event: MatSelectChange) {
      this.projects=[];
      this.ticketForm.get('project')?.setValue('NOTHING_SELECTED');
        if($event.value === this.systems[0].value){
          this.projects = this.projects_backend;
        }else{
          this.projects = this.projects_frontend;
        }
    }
}
