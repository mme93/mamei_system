import { TestBed } from '@angular/core/testing';

import { DatabaseProcessService } from './database-process.service';

describe('DatabaseProcessService', () => {
  let service: DatabaseProcessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatabaseProcessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
