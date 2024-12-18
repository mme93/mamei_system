import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './core/page/account/login/login.component';
import {ButtonModule} from "primeng/button";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CardModule} from "primeng/card";
import {SplitButtonModule} from "primeng/splitbutton";
import {InputTextModule} from "primeng/inputtext";
import {SelectButtonModule} from "primeng/selectbutton";
import {MenubarModule} from "primeng/menubar";
import {SqlComponent} from './core/page/sql/sql.component';
import {HttpClientModule} from "@angular/common/http";
import { SystemUserComponent } from './core/page/system-user/system-user.component';
import { TableMetadataDialogComponent } from './shared/dialog/table/table-metadata-dialog/table-metadata-dialog.component';
import {DialogModule} from "primeng/dialog";
import {ListboxModule} from "primeng/listbox";
import {DialogService} from "primeng/dynamicdialog";
import {TableModule} from "primeng/table";
import {PaginatorModule} from "primeng/paginator";
import {CheckboxModule} from "primeng/checkbox";
import {MessagesModule} from "primeng/messages";
import {MessageService} from "primeng/api";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SqlComponent,
    SystemUserComponent,
    TableMetadataDialogComponent
  ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        CommonModule,
        AppRoutingModule,
        ButtonModule,
        FormsModule,
        ReactiveFormsModule,
        CardModule,
        SplitButtonModule,
        InputTextModule,
        SelectButtonModule,
        MenubarModule,
        HttpClientModule,
        DialogModule,
        ListboxModule,
        TableModule,
        PaginatorModule,
        CheckboxModule,
        MessagesModule
    ],
  providers: [DialogService,MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
