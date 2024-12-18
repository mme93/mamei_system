import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PixelquestComponent } from './pixelquest/pixelquest.component';
import { PixelLoginComponent } from './pixelquest/pixel-login/pixel-login.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'pixelquest',
    component: PixelquestComponent
  },
  {
    path: 'login',
    component: PixelLoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
