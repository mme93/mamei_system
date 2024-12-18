import { TestBed } from '@angular/core/testing';

import { FilterProcessService } from './filter-process.service';

describe('FilterProcessService', () => {
  let service: FilterProcessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FilterProcessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
