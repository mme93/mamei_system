import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {LandingpageComponent} from "./pages/landingpage/landingpage.component";
import {SecurityGuard} from "./shared/guard/security.guard";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: LandingpageComponent,canActivate: [SecurityGuard] },
  {
    path: 'sudoku',
    loadChildren: () => import('./pages/sudoku/sudoku.module').then(m => m.SudokuComponentModule),
    canActivate: [SecurityGuard]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
