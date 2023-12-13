import { NgModule } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {HelpComponent} from "./help.component";
import {HelpRoutingModule} from "./help-routing.module";


@NgModule({
  imports: [
    HelpRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule
  ],
  declarations: [HelpComponent]
})
export class HelpModule {}
