import { NgModule } from '@angular/core';
import {SudokuComponent} from "./sudoku.component";
import {SudokuComponentRoutingModule} from "./sudoku-routing.module";
import { NormalComponent } from './level/normal/normal.component';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import { StandardComponent } from './level/standard/standard.component';
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";


@NgModule({
  imports: [
    SudokuComponentRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule
  ],
  declarations: [SudokuComponent, NormalComponent, StandardComponent]
})
export class SudokuComponentModule {}
