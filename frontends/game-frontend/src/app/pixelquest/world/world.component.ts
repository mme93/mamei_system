import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenSizeService } from '../../service/screen/screen-size.service';

export interface WorldGridRow {
  gridElements: WorldGridElement[];
}

export interface WorldGridElement {
  rowSize: number;
  colSize: number;
  rowIndex: number;
  colIndex: number;
  backgroundImg: string;
  hasPerson: boolean;
}

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit, OnDestroy {
  grid: WorldGridRow[] = [];
  example: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;
  rowStartPoint = 7;
  colStartPoint = 16;
  rows: number = 14;
  cols: number = 32;
  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenSizeService) { }

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
      this.blockHight = (size.height * 0.7) / this.rows;
      this.blockWidth = (size.width * 0.8) / this.cols;
    });
    this.createGrid();
  }

  createGrid() {
    for (let i = 0; i < this.rows; i++) {
      let x: String[] = [];
      let row: WorldGridRow = { gridElements: [] };
      let isStart = false;
      for (let j = 0; j < this.cols; j++) {
        x.push(String(((i * 10) + j + 1)))
        isStart = (j === this.colStartPoint && i == this.rowStartPoint);
        let backgroundImage: string = isStart ? './assets/fields/wood.png' : './assets/stone_ground_field.png';
        row.gridElements.push({
          rowSize: this.rows,
          colSize: this.cols,
          rowIndex: i,
          colIndex: j,
          backgroundImg: backgroundImage,
          hasPerson: isStart
        } as WorldGridElement)
        
      }
      this.example.push(x);

      this.grid.push(row);
    }

  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
