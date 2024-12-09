import { TestBed } from '@angular/core/testing';

import { FigurecontrollerService } from './figurecontroller.service';

describe('FigurecontrollerService', () => {
  let service: FigurecontrollerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FigurecontrollerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
