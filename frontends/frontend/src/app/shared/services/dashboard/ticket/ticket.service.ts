import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTicket, Ticket } from 'src/app/shared/model/dashboard/Ticket';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private createTicketUrl = environment.uri + ':9000/api/dashboard/ticket/';

  constructor(private http: HttpClient) {

  }

  createTicket(ticket: CreateTicket) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Ticket>(this.createTicketUrl, ticket, httpOptions);
  }

  getTicketById(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<Ticket>(this.createTicketUrl + id, httpOptions);
  }

  getAllTickets() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<Ticket[]>(this.createTicketUrl, httpOptions);
  }

  deleteTicket(ticket: Ticket) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.delete(this.createTicketUrl + ticket.id, httpOptions);
  }

  changeTicketStatus(status: string, id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<Ticket>(this.createTicketUrl + 'status/' + id, status, httpOptions);
  }

  updateTicket(ticket: Ticket | null) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.put(this.createTicketUrl, ticket, httpOptions);
  }

}
