import { TestBed } from '@angular/core/testing';

import { TicketTableFilterService } from './ticket-table-filter.service';

describe('TicketTableFilterService', () => {
  let service: TicketTableFilterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketTableFilterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
