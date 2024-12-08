import { Component, OnInit } from '@angular/core';
import { PixelQuestContainer } from '../model/pixelquestmodel';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrl: './world.component.scss'
})
export class WorldComponent implements OnInit {

  stone_ground_fiel_path = '/assets/stone_ground_field.png';

  container: PixelQuestContainer = {
    colSize: 50,
    rowSize: 50,
    pixelQuestGrid: {
      pixelRow: []
    },
    settings: {
      offsetX: 0,
      offsetY: 0,
      isDragging: false,
      lastMouseX: 0,
      lastMouseY: 0
    }
  }

  ngOnInit() {
    this.generateGrid();
  }

  generateGrid() {
    for (let row: number = 0; row < this.container.rowSize; row++) {
      if (!this.container.pixelQuestGrid.pixelRow[row]) {
        this.container.pixelQuestGrid.pixelRow[row] = { pixelElements: [] };
      }
      for (let col: number = 0; col < this.container.colSize; col++) {
        this.container.pixelQuestGrid.pixelRow[row].pixelElements.push({
          colIndex: col,
          rowIndex: row,
          image: this.stone_ground_fiel_path
        });
      }
    }
    console.log(this.container);
  }

  startDrag(event: MouseEvent) {
    this.container.settings.isDragging = true;
    this.container.settings.lastMouseX = event.clientX;
    this.container.settings.lastMouseY = event.clientY;
  }

  stopDrag() {
    this.container.settings.isDragging = false;
  }

  onDrag(event: MouseEvent) {
    if (this.container.settings.isDragging) {
      const deltaX = event.clientX - this.container.settings.lastMouseX;
      const deltaY = event.clientY - this.container.settings.lastMouseY;

      this.container.settings.offsetX += deltaX;
      this.container.settings.offsetY += deltaY;

      this.container.settings.lastMouseX = event.clientX;
      this.container.settings.lastMouseY = event.clientY;
    }
  }
}