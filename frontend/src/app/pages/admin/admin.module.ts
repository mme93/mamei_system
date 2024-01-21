import {NgModule} from '@angular/core';
import {AdminRoutingModule} from "./admin-routing.module";
import { DatabaseProcessComponent } from './database-process/database-process.component';
import {MatIconModule} from "@angular/material/icon";


@NgModule({
  imports: [
    AdminRoutingModule,
    MatIconModule
  ],
    exports: [
        DatabaseProcessComponent
    ],
  declarations: [
    DatabaseProcessComponent
  ]
})
export class AdminModule {
}
