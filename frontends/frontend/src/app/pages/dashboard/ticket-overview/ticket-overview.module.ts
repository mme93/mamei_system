import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TicketOverviewRoutingModule } from "./ticket-overview-routing.module";
import { MatCardModule } from "@angular/material/card";
import { CreateTicketComponent } from "./create-ticket/create-ticket.component";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { FlexLayoutModule } from "@angular/flex-layout";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatNativeDateModule } from "@angular/material/core";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatSelectModule } from "@angular/material/select";
import { TicketComponent } from "./ticket/ticket.component";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatProgressBarModule } from "@angular/material/progress-bar";



@NgModule({
    imports: [
        TicketOverviewRoutingModule,
        CommonModule,
        MatCardModule,
        MatInputModule,
        FlexLayoutModule,
        FormsModule,
        ReactiveFormsModule,
        MatSelectModule,
        MatCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatButtonModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatProgressBarModule
    ],
    exports: [],
    declarations: [
        CreateTicketComponent,
        TicketComponent
    ]
})
export class TicketOverviewModule { }


