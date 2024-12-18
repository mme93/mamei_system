import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ServerComponent} from "./server.component";
import {ServerOverviewComponent} from "./server-overview/server-overview.component";
import {ServerInfoComponent} from "./server-info/server-info.component";

const routes: Routes = [
  {
    path: '',
    component: ServerComponent
  },
  {
    path: 'overview',
    component: ServerOverviewComponent
  },
  {
    path: 'info',
    component: ServerInfoComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ServerRoutingModule {
}
