import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TicketTableFilter } from 'src/app/shared/model/settings/TicketSettings';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketTableFilterService {


  private createTicketUrl = environment.uri + ':9052/ticket/table/settings/';
  private ticketFilterUrl = environment.uri + ':9000/api/dashboard/ticket/table';

  constructor(private http: HttpClient) {
  }

  updateFilter(filter: TicketTableFilter) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<TicketTableFilter>(this.createTicketUrl, filter, httpOptions);
  }

  deleteFilter(filterName: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.delete(this.createTicketUrl + filterName, httpOptions);
  }

  getCurrentFilter() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<TicketTableFilter>(this.ticketFilterUrl, httpOptions);
  }

  getFilters() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<TicketTableFilter[]>(this.ticketFilterUrl+"s", httpOptions);
  }

}
