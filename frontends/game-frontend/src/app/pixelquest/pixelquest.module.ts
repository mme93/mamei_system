import { NgModule } from '@angular/core';
import { PixelquestComponent } from './pixelquest.component';
import { PixelquestRoutingModule } from './pixelquest-routing.module';


@NgModule({
  declarations: [
    PixelquestComponent
  ],
  imports: [
    PixelquestRoutingModule
    
  ],
  providers: [],
  bootstrap: [PixelquestComponent]
})
export class PixelQuestModule { }
