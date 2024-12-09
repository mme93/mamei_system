import { NgModule } from '@angular/core';
import { PixelquestComponent } from './pixelquest.component';
import { PixelquestRoutingModule } from './pixelquest-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { WorldComponent } from './world/world.component';
import { CommonModule } from '@angular/common';
import { WorldElementComponent } from './world/world-element/world-element.component';


@NgModule({
  declarations: [
    PixelquestComponent,
    HeaderComponent,
    FooterComponent,
    WorldComponent,
    WorldElementComponent
  ],
  imports: [
    PixelquestRoutingModule,
    CommonModule
    
  ],
  providers: [],
  bootstrap: [PixelquestComponent]
})
export class PixelQuestModule { }
