import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  {
    path: 'pixelquest',
    loadChildren: () => import('./pixelquest/pixelquest.module').then(m => m.PixelQuestModule)
  },
  { path: '', redirectTo: 'pixelquest', pathMatch: 'full' }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
