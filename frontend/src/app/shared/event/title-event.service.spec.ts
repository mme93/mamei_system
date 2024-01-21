import { TestBed } from '@angular/core/testing';

import { TitleEventService } from './title-event.service';

describe('TitleEventService', () => {
  let service: TitleEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TitleEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
