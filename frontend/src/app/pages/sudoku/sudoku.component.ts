import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sudoku',
  templateUrl: './sudoku.component.html',
  styleUrls: ['./sudoku.component.scss']
})
export class SudokuComponent {

  constructor(private router:Router) {
  }

  openLevel(difficulty: string) {
    if(difficulty === 'easy'){

    }else if(difficulty === 'normal'){
      this.router.navigate(['/sudoku/normal']);
    }
  }
}
