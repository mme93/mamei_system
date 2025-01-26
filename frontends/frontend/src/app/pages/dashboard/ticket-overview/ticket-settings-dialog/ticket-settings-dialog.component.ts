import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketTableFilter, TicketTableSettings, X } from 'src/app/shared/model/settings/TicketSettings';
import { TicketTableFilterService } from 'src/app/shared/services/dashboard/ticket/ticket-table-filter.service';

@Component({
  selector: 'app-ticket-settings-dialog',
  templateUrl: './ticket-settings-dialog.component.html',
  styleUrls: ['./ticket-settings-dialog.component.scss']
})
export class TicketSettingsDialogComponent implements OnInit{
  currentCopy: TicketTableFilter | undefined;
  isEditable = false;
  isNewFilter = false;
  settings: TicketTableSettings;
  filterNames: string[] = [];
  filterName = '';
  defaultColumns: string[] = [];
  newTicketFilterName = '';
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

  filters: X[] = [];
  selectedFilters: X = {
    filterName: '',
    done: true,
    create: true,
    in_PROGRESS: true,
    refinement: true,
    waiting: true,
    displayedColumns: []
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TicketTableSettings, private filterService: TicketTableFilterService,
    private dialogRef: MatDialogRef<TicketSettingsDialogComponent>
  ) {
    this.settings = { ...data };
    this.settings.filter.forEach(filter => this.filterNames.push(filter.name));
    const name = this.settings.selectedFilter.name;
    this.filterName = name;
    this.changeFilter(name);
  }
  ngOnInit(): void {
   this.filterService.getFilters().subscribe((result:X[])=>this.filters=result);
  }

  onCancle() {
    this.dialogRef.close();
  }

  onClose(): void {
    this.settings.selectedFilter = this.selectedFilter;
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
        displayedColumns: this.settings.defaultCoulmns
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

        this.currentCopy = {
          displayedColumns: [...filter.displayedColumns],
          name: filter.name,
          statusFilter: { ...filter.statusFilter }
        }

      }
    });
    this.isEditable = this.filterName === 'default';
    this.changeCheckbox();
  }
  changeIsAllCheckbox(): void {
    this.selectedFilter.statusFilter.isCREATED = this.isALL;
    this.selectedFilter.statusFilter.isWAITING = this.isALL;
    this.selectedFilter.statusFilter.isREFINEMENT = this.isALL;
    this.selectedFilter.statusFilter.isIN_PROGRESS = this.isALL;
    this.selectedFilter.statusFilter.isDONE = this.isALL;
  }
  changeCheckbox(): void {
    this.isALL = this.selectedFilter.statusFilter.isCREATED &&
      this.selectedFilter.statusFilter.isWAITING &&
      this.selectedFilter.statusFilter.isREFINEMENT &&
      this.selectedFilter.statusFilter.isIN_PROGRESS &&
      this.selectedFilter.statusFilter.isDONE;
  }

  resetRows() {
    this.defaultColumns = this.settings.defaultCoulmns;
    this.selectedFilter.displayedColumns = this.settings.defaultCoulmns;
  }
  newFilter() {
    this.isNewFilter = !this.isNewFilter;
  }

  editFilter() {
    this.isEditable = !this.isEditable;
  }

  createNewFilter() {
    this.filterService.updateFilter({
      name: this.newTicketFilterName,
      statusFilter: this.selectedFilter.statusFilter,
      displayedColumns: this.selectedFilter.displayedColumns
    } as TicketTableFilter);
  }

  updateFilter() {
    this.filterService.updateFilter(this.selectedFilter);
    this.onClose();
  }

  deleteFilter() {
    this.filterService.deleteFilter(this.filterName);
  }

}
