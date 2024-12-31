import { Injectable } from '@angular/core';
import { CanvasGame } from '../../model/canvas-grid';

@Injectable({
  providedIn: 'root',
})
export class MoveService {

  movePlayerWithKeyEvent(event: KeyboardEvent, game: CanvasGame): CanvasGame {
    const key = event.key;
    return this.movedPlayer(key, game);
  }

  movedPlayer(direction: string, game: CanvasGame): CanvasGame {
    game.moved = false;
    if (direction === 'up' || direction === 'w') {
      return this.moveUp(game);
    } else if (direction === 'right' || direction === 'd') {
      return this.moveRight(game);
    } else if (direction === 'down' || direction === 's') {
      return this.moveDown(game);
    } else if (direction === 'left' || direction === 'a') {
      return this.moveLeft(game);
    }
    return game;
  }

  private moveUp(game: CanvasGame): CanvasGame {
    if (game.player.playerY > 0) {
      if (game.player.playerY <= game.gridContent.visibleStartY && game.gridContent.visibleStartY > 0) {
        game.gridContent.visibleStartY--;
      } else {
        game.player.playerY--;
      }
      game.moved = true;
    }
    return game;
  }

  private moveDown(game: CanvasGame): CanvasGame {
    if (game.player.playerY < game.gridContent.totalHeight - 1) {
      if (game.player.playerY >= game.gridContent.visibleStartY + game.gridContent.visibleHeight - 1 && game.gridContent.visibleStartY + game.gridContent.visibleHeight < game.gridContent.totalHeight) {
        game.gridContent.visibleStartY++;
      } else {
        game.player.playerY++;
      }
      game.moved = true;

    }
    return game;
  }

  private moveRight(game: CanvasGame): CanvasGame {
    if (game.player.playerX < game.gridContent.totalWidth - 1) {
      if (game.player.playerX >= game.gridContent.visibleStartX + game.gridContent.visibleWidth - 1 && game.gridContent.visibleStartX + game.gridContent.visibleWidth < game.gridContent.totalWidth) {
        game.gridContent.visibleStartX++;
      } else {
        game.player.playerX++;
      }
      game.moved = true;
    }
    return game;
  }

  private moveLeft(game: CanvasGame): CanvasGame {
    if (game.player.playerX > 0) {
      if (game.player.playerX <= game.gridContent.visibleStartX && game.gridContent.visibleStartX > 0) {
        game.gridContent.visibleStartX--;
      } else {
        game.player.playerX--;
      }
      game.moved = true;
    }
    return game;
  }

}
