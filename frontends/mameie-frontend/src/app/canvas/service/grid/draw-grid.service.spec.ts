import { TestBed } from '@angular/core/testing';

import { DrawGridService } from './draw-grid.service';

describe('DrawGridService', () => {
  let service: DrawGridService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrawGridService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
