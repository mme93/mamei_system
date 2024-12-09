import { NgModule } from '@angular/core';
import { PixelquestComponent } from './pixelquest.component';
import { PixelquestRoutingModule } from './pixelquest-routing.module';
import { WorldComponent } from './world/world.component';
import { CommonModule } from '@angular/common';
import { WorldElementComponent } from './world/world-element/world-element.component';
import { ImageModule } from 'primeng/image';

@NgModule({
  declarations: [
    PixelquestComponent,
    WorldComponent,
    WorldElementComponent
  ],
  imports: [
    PixelquestRoutingModule,
    CommonModule,
    ImageModule
    
  ],
  providers: [],
  bootstrap: [PixelquestComponent]
})
export class PixelQuestModule { }
