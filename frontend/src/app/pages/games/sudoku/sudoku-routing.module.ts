import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SudokuComponent} from "./sudoku.component";
import {NormalComponent} from "./level/normal/normal.component";


const routes: Routes = [
  {
    path: '',
    redirectTo: 'menu',
    pathMatch: 'full'
  },
  {
    path: 'menu',
    component: SudokuComponent
  }
  ,
  {
    path: 'normal',
    component: NormalComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SudokuComponentRoutingModule {}
