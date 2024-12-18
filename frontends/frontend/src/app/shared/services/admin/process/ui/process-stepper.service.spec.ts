import { TestBed } from '@angular/core/testing';

import { ProcessStepperService } from './process-stepper.service';

describe('ProcessStepperService', () => {
  let service: ProcessStepperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProcessStepperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
