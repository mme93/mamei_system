import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GamesComponent} from "./games.component";
import {SecurityGuard} from "../../shared/guard/security.guard";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'board',
    pathMatch: 'full'
  },
  {
    path: 'board',
    component: GamesComponent
  }, {
    path: 'sudoku',
    loadChildren: () => import('././sudoku/sudoku.module').then(m => m.SudokuComponentModule),
    canActivate: [SecurityGuard]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GamesRoutingModule {
}
