import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketTableFilter, TicketTableSettings } from 'src/app/shared/model/settings/TicketSettings';

@Component({
  selector: 'app-ticket-settings-dialog',
  templateUrl: './ticket-settings-dialog.component.html',
  styleUrls: ['./ticket-settings-dialog.component.scss']
})
export class TicketSettingsDialogComponent {
  isEditable = false;
  settings: TicketTableSettings;
  filterNames: string[] = [];
  filterName = '';
  defaultColumns: string[] = [];
  selectedFilter: TicketTableFilter = {
    name: '',
    statusFilter: {
      isCREATED: false,
      isWAITING: false,
      isREFINEMENT: false,
      isIN_PROGRESS: false,
      isDONE: false
    },
    displayedColumns: []
  };
  isALL = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TicketTableSettings,
    private dialogRef: MatDialogRef<TicketSettingsDialogComponent>
  ) {
    this.settings = { ...data };
    this.settings.filter.forEach(filter => this.filterNames.push(filter.name));
    const name = this.settings.selectedFilter.name;
    this.filterName = name;
    this.changeFilter(name);
  }

  onCancle() {
    this.dialogRef.close();
  }

  onClose(): void {
    this.dialogRef.close(this.settings);
  }

  resetDefault(): void {
    if (this.filterName === 'default') {
      this.selectedFilter = {
        name: 'default',
        statusFilter: {
          isCREATED: true,
          isWAITING: true,
          isREFINEMENT: true,
          isIN_PROGRESS: true,
          isDONE: true
        },
        displayedColumns: ['position', 'id', 'status', 'label', 'classification', 'title', 'date', 'createDate', 'buttons']
      }
    }
  }

  changeFilter(filterName: string): void {
    this.settings.filter.forEach(filter => {
      if (filterName === filter.name) {
        this.selectedFilter.displayedColumns = [...filter.displayedColumns];
        this.selectedFilter.name = filter.name;
        this.selectedFilter.statusFilter = { ...filter.statusFilter };
        this.defaultColumns = [...filter.displayedColumns];
      }
    });
    this.isEditable = this.filterName === 'default';
    this.changeCheckbox();
  }

  changeCheckbox(): void {
    this.isALL = this.selectedFilter.statusFilter.isCREATED &&
      this.selectedFilter.statusFilter.isWAITING &&
      this.selectedFilter.statusFilter.isREFINEMENT &&
      this.selectedFilter.statusFilter.isIN_PROGRESS &&
      this.selectedFilter.statusFilter.isDONE;
  }

  createNewFilter() {
    throw new Error('Method not implemented.');
  }

  editFilter() {
    this.isEditable = !this.isEditable;
  }
}
