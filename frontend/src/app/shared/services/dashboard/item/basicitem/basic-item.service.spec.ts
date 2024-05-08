import { TestBed } from '@angular/core/testing';

import { BasicItemService } from './basic-item.service';

describe('BasicItemService', () => {
  let service: BasicItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
