import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TicketOverviewRoutingModule } from "./ticket-overview-routing.module";



@NgModule({
    imports: [
        TicketOverviewRoutingModule,
        CommonModule
    ],
    exports: [],
    declarations: []
})
export class TicketOverviewModule { }
