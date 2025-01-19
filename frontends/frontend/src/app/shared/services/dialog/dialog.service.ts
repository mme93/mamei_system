import { inject, Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TicketSettingsDialogComponent } from 'src/app/pages/dashboard/ticket-overview/ticket-settings-dialog/ticket-settings-dialog.component';
import { TicketTableSettings } from '../../model/settings/TicketSettings';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  readonly dialog = inject(MatDialog);

  constructor() { }

  openTicketSettingsDialog(settings: TicketTableSettings): Observable<TicketTableSettings> {
    const dialogRef = this.dialog.open(TicketSettingsDialogComponent, {
      data: settings,
      width:'50vw'
    });
    return dialogRef.afterClosed();
  }
}
