import { NgModule } from '@angular/core';
import {SudokuComponent} from "./sudoku.component";
import {SudokuComponentRoutingModule} from "./sudoku-routing.module";
import { NormalComponent } from './level/normal/normal.component';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";


@NgModule({
  imports: [
    SudokuComponentRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule
  ],
  declarations: [SudokuComponent, NormalComponent]
})
export class SudokuComponentModule {}
