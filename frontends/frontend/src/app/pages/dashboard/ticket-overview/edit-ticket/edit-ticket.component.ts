import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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

  readonly options = {
    types: [
      { value: 'TASK', viewValue: 'Task' },
      { value: 'BUG', viewValue: 'Bug' },
      { value: 'FEATURE', viewValue: 'Feature' }
    ],
    labels: [
      { value: 'CODE', viewValue: 'Code' },
      { value: 'NOTICE', viewValue: 'Notice' }
    ],
    classifications: [
      { value: 'HIGH', viewValue: 'High' },
      { value: 'MIDDLE', viewValue: 'Middle' },
      { value: 'LOW', viewValue: 'Low' }
    ]
  };

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
      classification: ['LOW', [Validators.required]]
    });
  }

  private loadTicket(id: number): void {
    this.ticketService.getTicketById(id).subscribe(
      (ticket: Ticket) => {
        this.ticket = ticket;
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
}
