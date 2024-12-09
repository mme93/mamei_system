import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit {
  container = {
    colSize: 3, // Gesamtanzahl der Spalten
    rowSize: 3, // Gesamtanzahl der Zeilen
    settings: {
      offsetX: 0,
      offsetY: 0,
      isDragging: false,
      lastMouseX: 0,
      lastMouseY: 0,
    },
    pixelQuestGrid: {
      pixelRow: Array(0).fill(null).map((_, rowIndex) => ({
        pixelElements: Array(0).fill(null).map((_, colIndex) => ({
          rowIndex,
          colIndex,
          image: '/assets/stone_ground_field.png',
        }))
      })),
    }
  };

  ngOnInit(): void {}

  startDrag(event: MouseEvent): void {
    this.container.settings.isDragging = true;
    this.container.settings.lastMouseX = event.clientX;
    this.container.settings.lastMouseY = event.clientY;
  }

  stopDrag(): void {
    this.container.settings.isDragging = false;
  }

  onDrag(event: MouseEvent): void {
    if (!this.container.settings.isDragging) return;

    const deltaX = event.clientX - this.container.settings.lastMouseX;
    const deltaY = event.clientY - this.container.settings.lastMouseY;

    this.container.settings.lastMouseX = event.clientX;
    this.container.settings.lastMouseY = event.clientY;

    // Grenzen prüfen (keine Felder außerhalb des sichtbaren Bereichs anzeigen)
    const maxOffsetX = -(this.container.colSize * 100 - 900); // 900px für 9 Spalten
    const maxOffsetY = -(this.container.rowSize * 100 - 900); // 900px für 9 Zeilen

    this.container.settings.offsetX = Math.min(0, Math.max(maxOffsetX, this.container.settings.offsetX + deltaX));
    this.container.settings.offsetY = Math.min(0, Math.max(maxOffsetY, this.container.settings.offsetY + deltaY));
  }
}
