import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PixelQuestModule } from './pixelquest/pixelquest.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PixelQuestModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
