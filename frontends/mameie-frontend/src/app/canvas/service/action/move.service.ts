import { Injectable } from '@angular/core';
import { CanvasGame } from '../../model/canvas-grid';

@Injectable({
  providedIn: 'root',
})
export class MoveService {

  moveFigure(direction: string, game: CanvasGame) {
    switch (direction) {
      case 'up':
        break;
      case 'down':
        break;
      case 'left':
        break;
      case 'right':
        break;
      case 'w':
        break;
      case 'a':
        break;
      case 's':
        break;
      case 'd':
        break;

    }

  }

  private changeFigure() {

  }

  private changeGrid() {

  }

}
