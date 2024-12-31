import { Injectable } from '@angular/core';
import { CanvasGame } from '../../model/canvas-grid';

@Injectable({
  providedIn: 'root',
})
export class DrawGridService {

  initCanvasGame(): CanvasGame {
    let playerImage = new Image();
    playerImage.src = '/assets/figure/figure.png';
    return {
      moved: false,
      gridContent: {
        totalWidth: 40,
        totalHeight: 40,
        visibleWidth: 36,
        visibleHeight: 16,
        tileSize: 32,
        offsetX: 0,
        offsetY: 0,
        visibleStartX: Math.max(0, Math.floor(40 / 2) - Math.floor(36 / 2)),
        visibleStartY: Math.max(0, Math.floor(40 / 2) - Math.floor(16 / 2)),
      },
      player: {
        image: playerImage,
        playerX: Math.floor(40 / 2),
        playerY: Math.floor(40 / 2)
      }
    }
  }

}
