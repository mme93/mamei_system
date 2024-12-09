import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PixelquestComponent } from './pixelquest.component';

const routes: Routes = [
    {
        path: 'world', component: PixelquestComponent
    },
    {
        path: '', redirectTo: 'world', pathMatch: 'full'
    }];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PixelquestRoutingModule { }