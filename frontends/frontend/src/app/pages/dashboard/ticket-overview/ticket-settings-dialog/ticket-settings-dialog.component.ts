import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketTableFilter } from 'src/app/shared/model/settings/TicketSettings';
import { TicketTableFilterService } from 'src/app/shared/services/dashboard/ticket/ticket-table-filter.service';

@Component({
  selector: 'app-ticket-settings-dialog',
  templateUrl: './ticket-settings-dialog.component.html',
  styleUrls: ['./ticket-settings-dialog.component.scss']
})
export class TicketSettingsDialogComponent implements OnInit {
  newTicketFilterName = '';
  isEditable = false;
  isNewFilter = false;
  isALL = false;
  filters: TicketTableFilter[] = [];
  selectedFilter: TicketTableFilter = {
    filterName: '',
    done: true,
    created: true,
    in_PROGRESS: true,
    refinement: true,
    waiting: true,
    displayedColumns: []
  };
  copyFilter = this.selectedFilter;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: TicketTableFilter, private filterService: TicketTableFilterService,
    private dialogRef: MatDialogRef<TicketSettingsDialogComponent>
  ) {
    this.selectedFilter = data;
  }


  ngOnInit(): void {
    this.filterService.getFilters().subscribe((result: TicketTableFilter[]) => this.filters = result);
  }

  onCancle() {
    this.dialogRef.close();
  }

  onClose(): void {
    this.dialogRef.close(this.selectedFilter);
  }

  resetDefault(): void {

  }

  changeFilter(filterName: string): void {
    this.filters.forEach(filter => {
      if (filterName === filter.filterName) {
        this.selectedFilter.displayedColumns = [...filter.displayedColumns];
        this.selectedFilter.filterName = filter.filterName;
        this.selectedFilter.created = filter.created;
        this.selectedFilter.in_PROGRESS = filter.in_PROGRESS;
        this.selectedFilter.refinement = filter.refinement;
        this.selectedFilter.waiting = filter.waiting;
        this.selectedFilter.done = filter.done;
        this.copyFilter.displayedColumns = [...filter.displayedColumns];
        this.copyFilter.filterName = filter.filterName;
        this.copyFilter.created = filter.created;
        this.copyFilter.in_PROGRESS = filter.in_PROGRESS;
        this.copyFilter.refinement = filter.refinement;
        this.copyFilter.waiting = filter.waiting;
        this.copyFilter.done = filter.done;
      }
    });
    this.isEditable = this.selectedFilter.filterName === 'Default';
    this.changeCheckbox();
  }
  changeIsAllCheckbox(): void {
    this.selectedFilter.created = this.isALL;
    this.selectedFilter.waiting = this.isALL;
    this.selectedFilter.refinement = this.isALL;
    this.selectedFilter.in_PROGRESS = this.isALL;
    this.selectedFilter.done = this.isALL;
  }
  changeCheckbox(): void {
    this.isALL = this.selectedFilter.created &&
      this.selectedFilter.waiting &&
      this.selectedFilter.refinement &&
      this.selectedFilter.in_PROGRESS &&
      this.selectedFilter.done;
  }

  resetRows() {
    this.selectedFilter.displayedColumns = this.selectedFilter.displayedColumns;
  }
  newFilter() {
    this.isNewFilter = !this.isNewFilter;
  }

  editFilter() {
    this.isEditable = !this.isEditable;
  }

  createNewFilter() {
    this.filterService.updateFilter({
      filterName: this.newTicketFilterName,
      done: this.selectedFilter.done,
      created: this.selectedFilter.created,
      in_PROGRESS: this.selectedFilter.in_PROGRESS,
      refinement: this.selectedFilter.refinement,
      waiting: this.selectedFilter.waiting,
      displayedColumns: []
    } as TicketTableFilter);
  }

  updateFilter() {
    this.filterService.updateFilter(this.selectedFilter).subscribe(() => {
      this.onClose();
    },
      (error: HttpErrorResponse) => {
        console.log('HTTP Status:', error.status);
      });
  }

  deleteFilter() {
    this.filterService.deleteFilter(this.selectedFilter.filterName);
  }
}
