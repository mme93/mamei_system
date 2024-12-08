import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrl: './world.component.scss'
})
export class WorldComponent implements OnInit {
  gridSize: number = 5; // Anzahl der sichtbaren Zellen n x n
  grid: Array<Array<{ x: number, y: number }>> = [];
  offsetX: number = 0; // Verschiebung entlang der X-Achse
  offsetY: number = 0; // Verschiebung entlang der Y-Achse
  isDragging: boolean = false;
  lastMouseX: number = 0;
  lastMouseY: number = 0;

  ngOnInit() {
    this.generateGrid();
  }

  generateGrid() {
    this.grid = Array.from({ length: this.gridSize }, (_, rowIndex) =>
      Array.from({ length: this.gridSize }, (_, colIndex) => ({
        x: colIndex,
        y: rowIndex
      }))
    );
    console.log(this.grid)
  }

  startDrag(event: MouseEvent) {
    this.isDragging = true;
    this.lastMouseX = event.clientX;
    this.lastMouseY = event.clientY;
  }

  stopDrag() {
    this.isDragging = false;
  }

  onDrag(event: MouseEvent) {
    if (this.isDragging) {
      const deltaX = event.clientX - this.lastMouseX;
      const deltaY = event.clientY - this.lastMouseY;

      this.offsetX += deltaX;
      this.offsetY += deltaY;

      this.lastMouseX = event.clientX;
      this.lastMouseY = event.clientY;
    }
  }
}