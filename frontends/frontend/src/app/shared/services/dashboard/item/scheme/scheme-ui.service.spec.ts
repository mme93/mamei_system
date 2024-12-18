import { TestBed } from '@angular/core/testing';

import { SchemeUiService } from './scheme-ui.service';

describe('SchemeUiService', () => {
  let service: SchemeUiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SchemeUiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
