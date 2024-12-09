import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit {

  test: String[][] = [];
  isDragging = false;
  lastMouseX = 0;
  lastMouseY = 0;
  offsetX = 0;
  offsetY = 0;

  ngOnInit(): void {
    this.createGridElements();
  }

  createGridElements() {
    const row = 5;
    const col = 10;
    for (var i: number = 0; i < row; i++) {
      let rowArray: String[] = [];
      for (var j: number = 0; j < col; j++){
        rowArray.push(String((i * 10) + 1 + j));
      }
      this.test.push(rowArray);
    }
  }

  startDrag(event: MouseEvent): void {
    this.isDragging = true;
    this.lastMouseX = event.clientX;
    this.lastMouseY = event.clientY;
  }

  stopDrag(): void {
    this.isDragging = false;
  }

  onDrag(event: MouseEvent): void {
    if (!this.isDragging) return;

    const deltaX = event.clientX - this.lastMouseX;
    const deltaY = event.clientY - this.lastMouseY;

    this.offsetX += deltaX;
    this.offsetY += deltaY;

    this.lastMouseX = event.clientX;
    this.lastMouseY = event.clientY;
  }
}
