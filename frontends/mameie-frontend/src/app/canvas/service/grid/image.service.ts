import { Injectable } from '@angular/core';
import { CanvasGameField } from '../../model/canvas-grid';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() { }

  createMap(): CanvasGameField[][] {
    let grid: CanvasGameField[][] = [];
    for (let i: number = 0; i < 40; i++) {
      let row: CanvasGameField[] = []
      for (let j: number = 0; j < 40; j++) {
        row.push({ path: '/assets/fields/wood.png' });
      }
      grid.push(row);
    }
    return grid;
  }

}
