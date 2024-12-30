import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-canvas-content',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './canvas-content.component.html',
  styleUrl: './canvas-content.component.scss'
})
export class CanvasContentComponent implements OnInit {
  @ViewChild('gridCanvas', { static: true }) gridCanvas!: ElementRef<HTMLCanvasElement>;

  totalWidth = 40;
  totalHeight = 40;
  visibleWidth = 36;
  visibleHeight = 16;
  tileSize = 32;
  offsetX = 0;
  offsetY = 0;

  playerX = Math.floor(this.totalWidth / 2);
  playerY = Math.floor(this.totalHeight / 2);

  visibleStartX = Math.max(0, this.playerX - Math.floor(this.visibleWidth / 2));
  visibleStartY = Math.max(0, this.playerY - Math.floor(this.visibleHeight / 2));

  private ctx!: CanvasRenderingContext2D;
  private playerImage = new Image();
  private uploadedImage: HTMLImageElement | null = null;

  ngOnInit(): void {
    this.initializeCanvas();
    this.drawGrid();

    window.addEventListener('resize', () => {
      this.initializeCanvas();
      this.drawGrid();
    });

    document.addEventListener('keydown', (event) => this.movePlayer(event));
  }

  initializeCanvas(): void {
    const canvas = this.gridCanvas.nativeElement;
    this.ctx = canvas.getContext('2d')!;


    const availableWidth = window.innerWidth * 0.8;
    const availableHeight = window.innerHeight * 0.8;

    this.tileSize = Math.min(
      Math.floor(availableWidth / this.visibleWidth),
      Math.floor(availableHeight / this.visibleHeight)
    );

    canvas.width = this.tileSize * this.visibleWidth;
    canvas.height = this.tileSize * this.visibleHeight;

    this.playerImage.src = '/assets/figure/figure.png'; // Path to player image
  }

  drawGrid(): void {
    this.ctx.clearRect(0, 0, this.gridCanvas.nativeElement.width, this.gridCanvas.nativeElement.height);

    for (let y = 0; y < this.visibleHeight; y++) {
      for (let x = 0; x < this.visibleWidth; x++) {
        const gridX = this.visibleStartX + x;
        const gridY = this.visibleStartY + y;

        this.ctx.fillStyle = (gridX + gridY) % 2 === 0 ? '#ffffff' : '#000000';
        this.ctx.fillRect(x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);

        this.ctx.strokeStyle = '#ccc';
        this.ctx.strokeRect(x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);

        if (gridX === this.playerX && gridY === this.playerY) {
          this.ctx.drawImage(this.playerImage, x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
        }

        if (this.uploadedImage && gridX === this.playerX && gridY === this.playerY) {
          this.ctx.drawImage(this.uploadedImage, x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
        }
      }
    }
  }

  movePlayer(event: KeyboardEvent): void {
    const key = event.key;
    let moved = false;

    if (key === 'w' && this.playerY > 0) {
      if (this.playerY <= this.visibleStartY && this.visibleStartY > 0) {
        this.visibleStartY--;
      } else {
        this.playerY--;
      }
      moved = true;
    }

    if (key === 's' && this.playerY < this.totalHeight - 1) {
      if (this.playerY >= this.visibleStartY + this.visibleHeight - 1 && this.visibleStartY + this.visibleHeight < this.totalHeight) {
        this.visibleStartY++;
      } else {
        this.playerY++;
      }
      moved = true;
    }

    if (key === 'a' && this.playerX > 0) {
      if (this.playerX <= this.visibleStartX && this.visibleStartX > 0) {
        this.visibleStartX--;
      } else {
        this.playerX--;
      }
      moved = true;
    }

    if (key === 'd' && this.playerX < this.totalWidth - 1) {
      if (this.playerX >= this.visibleStartX + this.visibleWidth - 1 && this.visibleStartX + this.visibleWidth < this.totalWidth) {
        this.visibleStartX++;
      } else {
        this.playerX++;
      }
      moved = true;
    }

    if (moved) {
      this.drawGrid();
    }
  }

  onImageUpload(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      const img = new Image();
      img.onload = () => {
        this.uploadedImage = img;
        this.drawGrid();
      };
      img.src = URL.createObjectURL(file);
    }
  }

  generateGrid(): void {
    this.initializeCanvas();
    this.drawGrid();
  }

  move(direction: string): void {
    let moved = false;
    switch (direction) {
      case 'up':
        if (this.playerY > 0) {
          if (this.playerY <= this.visibleStartY && this.visibleStartY > 0) {
            this.visibleStartY--;
          } else {
            this.playerY--;
          }
          moved = true;
        }
        break;
      case 'down':
        if (this.playerY < this.totalHeight - 1) {
          if (this.playerY >= this.visibleStartY + this.visibleHeight - 1 && this.visibleStartY + this.visibleHeight < this.totalHeight) {
            this.visibleStartY++;
          } else {
            this.playerY++;
          }
          moved = true;
        }
        break;
      case 'left':
        if (this.playerX > 0) {
          if (this.playerX <= this.visibleStartX && this.visibleStartX > 0) {
            this.visibleStartX--;
          } else {
            this.playerX--;
          }
          moved = true;
        }
        break;
      case 'right':
        if (this.playerX < this.totalWidth - 1) {
          if (this.playerX >= this.visibleStartX + this.visibleWidth - 1 && this.visibleStartX + this.visibleWidth < this.totalWidth) {
            this.visibleStartX++;
          } else {
            this.playerX++;
          }
          moved = true;
        }
        break;
    }
  
    if (moved) {
      this.drawGrid();
    }
  }
  
}
