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
import { MatMenuModule } from "@angular/material/menu";
import { EditTicketComponent } from "./edit-ticket/edit-ticket.component";
import { OverlayModule } from "@angular/cdk/overlay";
import { TicketSettingsDialogComponent } from "./ticket-settings-dialog/ticket-settings-dialog.component";
import { MatDialogModule } from "@angular/material/dialog";



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
        MatProgressBarModule,
        MatMenuModule,
        OverlayModule,
        MatDialogModule
    ],
    exports: [],
    declarations: [
        CreateTicketComponent,
        TicketComponent,
        EditTicketComponent,
        TicketSettingsDialogComponent
    ]
})
export class TicketOverviewModule { }


