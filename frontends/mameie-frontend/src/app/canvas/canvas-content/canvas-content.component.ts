import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CanvasGame } from '../model/canvas-grid';
import { DrawGridService } from '../service/grid/draw-grid.service';
import { MoveService } from '../service/action/move.service';

@Component({
  selector: 'app-canvas-content',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './canvas-content.component.html',
  styleUrl: './canvas-content.component.scss'
})
export class CanvasContentComponent implements OnInit {
  @ViewChild('gridCanvas', { static: true }) gridCanvas!: ElementRef<HTMLCanvasElement>;

  game: CanvasGame = this.drawGridService.initCanvasGame();
  ctx!: CanvasRenderingContext2D;

  constructor(private drawGridService: DrawGridService, private moveService: MoveService) { }

  ngOnInit(): void {
    this.ctx = this.drawGridService.initializeCanvas(this.game, this.gridCanvas.nativeElement);
    this.drawGridService.drawGrid(this.game, this.ctx, this.gridCanvas.nativeElement);

    window.addEventListener('resize', () => {
      this.ctx = this.drawGridService.initializeCanvas(this.game, this.gridCanvas.nativeElement);
      this.drawGridService.drawGrid(this.game, this.ctx, this.gridCanvas.nativeElement);
    });

    document.addEventListener('keydown', (event) => this.movePlayer(event));
  }

  movePlayer(event: KeyboardEvent): void {
    this.game = this.moveService.movePlayerWithKeyEvent(event, this.game);

    if (this.game.moved) {
      this.drawGridService.drawGrid(this.game, this.ctx, this.gridCanvas.nativeElement);
    }
  }

  move(direction: string): void {
    this.game = this.moveService.movedPlayer(direction, this.game);
    if (this.game.moved) {
      this.drawGridService.drawGrid(this.game, this.ctx, this.gridCanvas.nativeElement);
    }
  }

}
