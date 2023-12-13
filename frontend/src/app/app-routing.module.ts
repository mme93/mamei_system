import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {LandingpageComponent} from "./pages/landingpage/landingpage.component";
import {SecurityGuard} from "./shared/guard/security.guard";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: LandingpageComponent,canActivate: [SecurityGuard] },
  {
    path: 'games',
    loadChildren: () => import('./pages/games/games.module').then(m => m.GamesModule),
    canActivate: [SecurityGuard]
  },
  {
    path: 'dashboard',
    loadChildren: () => import('./pages/dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [SecurityGuard]
  },
  {
    path: 'help',
    loadChildren: () => import('./pages/help/help.module').then(m => m.HelpModule),
    canActivate: [SecurityGuard]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
