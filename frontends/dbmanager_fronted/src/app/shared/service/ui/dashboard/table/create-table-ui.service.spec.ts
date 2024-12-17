import { TestBed } from '@angular/core/testing';

import { CreateTableUiService } from './create-table-ui.service';

describe('CreateTableUiService', () => {
  let service: CreateTableUiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateTableUiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
