import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TicketTableFilter } from 'src/app/shared/model/settings/TicketSettings';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketTableFilterService {


  private createTicketUrl = environment.uri + ':9052/ticket/table/settings/';
  private ticketFilterUrl = environment.uri + ':9052/ticket/filter';

  constructor(private http: HttpClient) {
  }

  updateFilter(filter: TicketTableFilter) {
    return this.http.put<TicketTableFilter>(this.createTicketUrl, filter);
  }

  deleteFilter(filterName: string) {
    return this.http.delete(this.createTicketUrl + filterName);
  }

  getFilterById(id: number) {
    return this.http.get<TicketTableFilter>(this.ticketFilterUrl + "/table/" + id);
  }

  getFilters() {
    return this.http.get<TicketTableFilter[]>(this.ticketFilterUrl + "/table");
  }

}
