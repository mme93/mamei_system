import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PixelQuestModule } from './pixelquest/pixelquest.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from "@angular/forms";
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    InputTextModule,
    AppRoutingModule,
    PixelQuestModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
