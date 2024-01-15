import { NgModule } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {SettingsComponent} from "./settings.component";
import {SettingsRoutingModule} from "./settings-routing.module";
import { ServiceStatusComponent } from './service-status/service-status.component';
import {InformationComponent} from "../information/information.component";
import { AdminstrationComponent } from './adminstration/adminstration.component';
import {AccountComponent} from "./account/account.component";


@NgModule({
  imports: [
    SettingsRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule
  ],
  declarations: [SettingsComponent, ServiceStatusComponent, AdminstrationComponent,AccountComponent]
})
export class SettingsModule {

  showComponent(component: string) {
    console.log(`Show component: ${component}`);
  }

}
