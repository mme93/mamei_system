import {NgModule} from "@angular/core";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatNativeDateModule} from "@angular/material/core";
import {MatTableModule} from "@angular/material/table";
import {FlexLayoutModule} from "@angular/flex-layout";
import {CreateItemComponent} from "./create-item/create-item.component";
import {EditItemComponent} from "./edit-item/edit-item.component";
import {ItemRoutingModule} from "./item-routing.module";
import {MatSelectModule} from "@angular/material/select";
import {MatDividerModule} from "@angular/material/divider";
import {MatListModule} from "@angular/material/list";
import { SchemeComponent } from './scheme/scheme.component';
import { ViewSchemeComponent } from './scheme/view-scheme/view-scheme.component';
import { EditSchemeComponent } from './scheme/edit-scheme/edit-scheme.component';
import {MatStepperModule} from "@angular/material/stepper";
import { ComponentForSchemeComponent } from './create-item/component-for-scheme/component-for-scheme.component';
import { ItemSetupComponent } from './create-item/item-setup/item-setup.component';
import { ItemRealViewComponent } from './create-item/item-real-view/item-real-view.component';
import {MatChipsModule} from "@angular/material/chips";

@NgModule({
  imports: [
    ItemRoutingModule,
    MatCardModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule,
    MatRadioModule,
    MatCheckboxModule,
    MatInputModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    MatNativeDateModule,
    MatTableModule,
    FlexLayoutModule,
    MatSelectModule,
    FormsModule,
    MatDividerModule,
    MatListModule,
    MatStepperModule,
    MatChipsModule
  ],
  declarations: [
    CreateItemComponent,
    EditItemComponent,
    SchemeComponent,
    ViewSchemeComponent,
    EditSchemeComponent,
    ComponentForSchemeComponent,
    ItemSetupComponent,
    ItemRealViewComponent
  ]
})
export class ItemModule {}
