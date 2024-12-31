import { Injectable } from '@angular/core';
import { CanvasGame, CanvasGameField } from '../../model/canvas-grid';

@Injectable({
  providedIn: 'root',
})
export class DrawGridService {

  drawGrid(game: CanvasGame, ctx: CanvasRenderingContext2D, nativeElement: HTMLCanvasElement, grid: CanvasGameField[][]) {
    ctx.clearRect(0, 0, nativeElement.width, nativeElement.height);

    for (let y = 0; y < game.gridContent.visibleHeight; y++) {
      for (let x = 0; x < game.gridContent.visibleWidth; x++) {
        const gridX = game.gridContent.visibleStartX + x;
        const gridY = game.gridContent.visibleStartY + y;

        ctx.fillStyle = (gridX + gridY) % 2 === 0 ? '#ffffff' : '#000000';
        ctx.fillRect(x * game.gridContent.tileSize, y * game.gridContent.tileSize, game.gridContent.tileSize, game.gridContent.tileSize);

        ctx.strokeStyle = '#ccc';
        ctx.strokeRect(x * game.gridContent.tileSize, y * game.gridContent.tileSize, game.gridContent.tileSize, game.gridContent.tileSize);

        if (gridX === game.player.playerX && gridY === game.player.playerY) {
          ctx.drawImage(game.player.image, x * game.gridContent.tileSize, y * game.gridContent.tileSize, game.gridContent.tileSize, game.gridContent.tileSize);
        }
      }
    }

  }

  initializeCanvas(game: CanvasGame, nativeElement: HTMLCanvasElement): CanvasRenderingContext2D {
    const canvas = nativeElement;
    const availableWidth = window.innerWidth * 0.8;
    const availableHeight = window.innerHeight * 0.8;

    game.gridContent.tileSize = Math.min(
      Math.floor(availableWidth / game.gridContent.visibleWidth),
      Math.floor(availableHeight / game.gridContent.visibleHeight)
    );

    canvas.width = game.gridContent.tileSize * game.gridContent.visibleWidth;
    canvas.height = game.gridContent.tileSize * game.gridContent.visibleHeight;

    game.player.image.src = '/assets/figure/figure.png';

    return canvas.getContext('2d')!;
  }


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
