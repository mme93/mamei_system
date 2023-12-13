import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SudokuLevelService} from "../../../shared/services/sudoku/sudoku-level.service";

@Component({
  selector: 'app-sudoku',
  templateUrl: './sudoku.component.html',
  styleUrls: ['./sudoku.component.scss']
})
export class SudokuComponent implements OnInit {

  constructor(private router: Router, private sudokuService: SudokuLevelService) {
  }

  ngOnInit(): void {
    this.sudokuService.loadSudokuLevelList();
  }

  openLevel(difficulty: string) {
    if (difficulty === 'easy') {

    } else if (difficulty === 'normal') {
      this.router.navigate(['/sudoku/normal']);
    }
  }
}
