import { NgModule } from '@angular/core';
import { PixelquestComponent } from './pixelquest.component';
import { PixelquestRoutingModule } from './pixelquest-routing.module';
import { WorldComponent } from './world/world.component';
import { CommonModule } from '@angular/common';
import { WorldElementComponent } from './world/world-element/world-element.component';
import { ImageModule } from 'primeng/image';
import { HttpClientModule } from '@angular/common/http';
import { MapService } from '../service/data/map/map.service';
import { AccountService } from '../service/data/account/account.service';
import { LoginComponent } from './login/login.component';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import {CardModule} from "primeng/card";
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  declarations: [
    PixelquestComponent,
    WorldComponent,
    WorldElementComponent,
    LoginComponent
  ],
  imports: [
    PixelquestRoutingModule,
    CommonModule,
    ImageModule,
    HttpClientModule,
    PanelModule,
    CommonModule,
    ButtonModule,
    CardModule,
    FormsModule,
    InputTextModule
  ],
  providers: [MapService, AccountService],
  bootstrap: [PixelquestComponent]
})
export class PixelQuestModule { }
