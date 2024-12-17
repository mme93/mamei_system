import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {TableNames} from "../../../../../shared/model/server/database/table/Table";
import {PaginatorState} from "primeng/paginator";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-database-settings',
  templateUrl: './database-settings.component.html',
  styleUrl: './database-settings.component.scss'
})
export class DatabaseSettingsComponent implements OnInit {
  database: string | null = '';
  server: string | null = '';
  copyTableNames: TableNames[] = []
  tableNames: TableNames[] = []
  page: number = 0;
  rows: number = 5;
  tableName = '';
  tableNameExist = false;

  constructor(private tableService: TableService, private router: Router, private route: ActivatedRoute,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.database = params['databaseName'];
      this.server = localStorage.getItem('server');
    });

    this.tableService.getAllTableNames(this.server, this.database).subscribe(result => {
      for (let i = 0; i < result.length; i++) {
        this.tableNames.push({
          nr: i + 1,
          name: result[i]
        });
        this.copyTableNames.push({
          nr: i + 1,
          name: result[i]
        });
      }
      this.updatePage();
    });
  }

  change($event: PaginatorState) {
    this.page = $event.page!;
    this.rows = $event.rows!;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page! * this.rows!;
    const end = start + this.rows!;
    this.tableNames = this.copyTableNames.slice(start, end);
  }

  openTable(tableName: string) {
    this.router.navigate(['/dashboard/table/show', tableName, this.database]);
  }

  createTable() {
    if (this.copyTableNames.filter(tableName => tableName.name === this.tableName).length === 1) {
      this.messageService.add({
        severity: "error",
        summary: 'All ready exist.',
        detail: 'Table with the name ' + this.tableName + ' all ready exist in Database ' + this.database,
      });
    } else {
      this.router.navigate(['/dashboard/table/create', this.tableName, this.database]);
    }

  }
}
