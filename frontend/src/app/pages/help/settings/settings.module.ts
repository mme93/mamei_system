import { NgModule } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import { CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {SettingsComponent} from "./settings.component";
import {SettingsRoutingModule} from "./settings-routing.module";
import { ServiceStatusComponent } from './service-status/service-status.component';
import { AdminstrationComponent } from './adminstration/adminstration.component';
import {AccountComponent} from "./account/account.component";
import {MatMenuModule} from "@angular/material/menu";
import { UiSettingsComponent } from './ui-settings/ui-settings.component';
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";


@NgModule({
  imports: [
    SettingsRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule,
    MatFormFieldModule,
    FormsModule
  ],
  declarations: [SettingsComponent, ServiceStatusComponent, AdminstrationComponent,AccountComponent, UiSettingsComponent]
})
export class SettingsModule {

}
