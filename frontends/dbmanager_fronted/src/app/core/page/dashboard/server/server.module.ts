import {NgModule} from "@angular/core";
import {ServerRoutingModule} from "./server-routing.module";
import {ServerInfoComponent} from './server-info/server-info.component';
import {TreeModule} from "primeng/tree";

@NgModule({
  imports: [
    ServerRoutingModule,
    TreeModule
  ],
  exports: [],
  declarations: [ServerInfoComponent]
})
export class ServerModule {
}


