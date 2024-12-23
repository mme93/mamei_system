import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PixelquestComponent } from './pixelquest/pixelquest.component';
import { PixelLoginComponent } from './pixelquest/pixel-login/pixel-login.component';
import { PixelquestMapEditorComponent } from './pixelquest/pixelquest-map-editor/pixelquest-map-editor.component';
import { TestEditorComponent } from './pixelquest/test-editor/test-editor.component';

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
  },
  {
    path: 'editor',
    component: PixelquestMapEditorComponent
  },
  {
    path: 'test',
    component: TestEditorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
