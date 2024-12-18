import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {TableMetadata, TableMetadataView} from "../../../model/table/TableView";
import {PaginatorState} from "primeng/paginator";

@Component({
  selector: 'app-table-metadata-dialog',
  templateUrl: './table-metadata-dialog.component.html',
  styleUrl: './table-metadata-dialog.component.scss'
})
export class TableMetadataDialogComponent implements OnInit {
  page: number = 0;
  rows: number = 30;
  isEdit: boolean = false;
  columnNames: string[] = ['Nr', 'Name', 'Type', 'Is nullable', 'Key', 'Default Value']
  metaData: TableMetadataView[] = [];
  filteredMetaData: TableMetadataView[] = [];

  constructor(public ref: DynamicDialogRef, private dialogService: DialogService, private config: DynamicDialogConfig) {
  }

  ngOnInit(): void {
    this.mapToTableView(this.config.data.metaData)
    this.updatePage();
  }

  change($event: PaginatorState) {
    this.page = $event.page!;
    this.rows = $event.rows!;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page! * this.rows!;
    const end = start + this.rows!;
    this.filteredMetaData = this.metaData.slice(start, end);
  }

  addMetaData() {
    this.filteredMetaData.push({nr: this.metaData.length + 1});
    this.metaData.push({nr: this.metaData.length + 1});
  }

  changeColumnNames() {
    this.isEdit = !this.isEdit;
    if (this.isEdit) {
      this.columnNames = ['Nr', 'Select', 'Name', 'Type', 'Is nullable', 'Key', 'Default Value', 'Overwrite Value'];
    } else {
      this.columnNames = ['Nr', 'Name', 'Type', 'Is nullable', 'Key', 'Default Value'];
    }
  }

  mapToTableView(metaData: TableMetadata[]) {
    this.metaData = [];
    this.filteredMetaData = [];
    for (let i = 0; i < metaData.length; i++) {
      let tableView = {
        nr: i + 1,
        isNew: false,
        isSelected: false,
        overWriteValue: '',
        field: metaData[i].field,
        type: metaData[i].type,
        nullable: metaData[i].nullable,
        key: metaData[i].key,
        defaultValue: metaData[i].defaultValue
      };
      this.metaData.push(tableView)
    }

  }

  cancel() {
    this.changeColumnNames();
    this.mapToTableView(this.config.data.metaData)
    this.updatePage();
  }

  //TODO: Überarbeiten
  deleteRows() {
    console.log(this.metaData)
    console.log(this.filteredMetaData)
    let updatedMetaData: TableMetadataView[] = [];
    let index = 1;
    for (let i = 0; i < this.metaData.length; i++) {
      if (!this.filteredMetaData[i].isSelected) {
        this.metaData[i].nr = index;
        updatedMetaData.push(this.filteredMetaData[i]);
        index++;
      }
    }
    this.metaData = updatedMetaData;
    this.updatePage();
  }
}
