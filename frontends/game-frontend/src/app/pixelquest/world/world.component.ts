import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit {

  test: String[][] = [];

  ngOnInit(): void {
    this.createGridElements();
  }

  createGridElements() {
    const row = 50;
    const col = 50;
    for (var i: number = 0; i < row; i++) {
      let rowArray: String[] = [];
      for (var j: number = 0; j < col; j++){
        rowArray.push(String((i * 10) + 1 + j));
      }
      this.test.push(rowArray);
    }
  }
}
