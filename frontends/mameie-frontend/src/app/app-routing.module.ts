import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PixelquestComponent } from './pixelquest/pixelquest.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'pixelquest',
    pathMatch: 'full'
  },
  {
    path: 'pixelquest',
    component: PixelquestComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
