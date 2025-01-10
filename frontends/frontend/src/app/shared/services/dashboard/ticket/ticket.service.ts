import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTicket, Ticket } from 'src/app/shared/model/dashboard/Ticket';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
 

  private createTicketUrl = environment.uri+':9052/ticket/';

  constructor(private http: HttpClient) {
  }

  createTicket(ticket:CreateTicket) {
     return this.http.post<Ticket>(this.createTicketUrl,ticket);
  }

  getTicketById(id:number){
    return this.http.get<Ticket>(this.createTicketUrl+id);
  }

  getAllTickets() {
    return this.http.get<Ticket[]>(this.createTicketUrl);
  }

}
