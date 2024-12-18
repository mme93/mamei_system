import { TestBed } from '@angular/core/testing';

import { SudokuLevelService } from './sudoku-level.service';

describe('SudokuLevelService', () => {
  let service: SudokuLevelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SudokuLevelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
