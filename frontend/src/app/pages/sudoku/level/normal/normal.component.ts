import { Component } from '@angular/core';

@Component({
  selector: 'app-normal',
  templateUrl: './normal.component.html',
  styleUrls: ['./normal.component.scss']
})
export class NormalComponent {
  sudokuGrid: number[][] = [
    [3, 4, 8, 7, 6, 2, 5, 1, 9],
    [9, 6, 5, 4, 1, 3, 2, 8, 7],
    [2, 1, 7, 5, 9, 8, 3, 6, 4],
    [5, 9, 3, 8, 4, 6, 7, 2, 1],
    [7, 2, 6, 3, 5, 1, 4, 9, 8],
    [4, 8, 1, 2, 7, 9, 6, 3, 5],
    [8, 3, 4, 9, 2, 7, 1, 5, 6],
    [6, 7, 2, 1, 8, 5, 9, 4, 3],
    [1, 5, 9, 6, 3, 4, 8, 7, 2]
  ];
}
