import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {LandingpageComponent} from "./pages/landingpage/landingpage.component";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: LandingpageComponent },
  {
    path: 'sudoku',
    loadChildren: () => import('./pages/sudoku/sudoku.module').then(m => m.SudokuComponentModule)
  },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
