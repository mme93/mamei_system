import { TestBed } from '@angular/core/testing';

import { CreateSudokuGridService } from './create-sudoku-grid.service';

describe('CreateSudokuGridService', () => {
  let service: CreateSudokuGridService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateSudokuGridService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
