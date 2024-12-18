import {Component, OnInit} from '@angular/core';
import {DatabaseService} from "../../../../shared/service/http/database/database.service";
import {DatabaseNameTable} from "../../../../shared/model/server/database/Database";
import {PaginatorState} from "primeng/paginator";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-database',
  templateUrl: './database.component.html',
  styleUrl: './database.component.scss'
})
export class DatabaseComponent implements OnInit {
  databaseNameExist = false;
  filteredDatabaseNames: DatabaseNameTable[] = [];
  databaseNames: DatabaseNameTable[] = [];
  page: number = 0;
  rows: number = 5;
  databaseName = '';
  serverName = localStorage.getItem('server');

  constructor(private databaseService: DatabaseService, private router: Router,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.loadTableData();
  }

  loadTableData() {
    this.databaseService.getAllDatabaseNames(this.serverName).subscribe(result => {
      for (let i = 0; i < result.length; i++) {
        this.databaseNames.push({
          nr: i + 1,
          name: result[i]
        });
        this.filteredDatabaseNames.push({
          nr: i + 1,
          name: result[i]
        });
      }
      this.updatePage();
    })
  }

  delete(databaseName: string) {
    this.databaseService.deleteDatabase(this.serverName, databaseName).subscribe(
      result => {
        let searchDatabaseName: DatabaseNameTable[] = [];
        this.databaseNames.forEach(databaseNameList => {
          if (databaseNameList.name !== databaseName) {
            searchDatabaseName.push(databaseNameList);
          }
        })
        this.databaseNames = [];
        this.databaseNames = searchDatabaseName;
        this.updatePage();
      }
    );
  }

  open(databaseName: string) {
    this.router.navigate(['/dashboard/database/settings', databaseName])
  }


  change($event: PaginatorState) {
    this.page = $event.page!;
    this.rows = $event.rows!;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page! * this.rows!;
    const end = start + this.rows!;
    this.filteredDatabaseNames = this.databaseNames.slice(start, end);
  }

  createDatabase() {
    this.databaseService.createDatabase(this.serverName, this.databaseName).subscribe(
      next => {
        let searchDatabaseName: DatabaseNameTable[] = [
          {
            nr: 1,
            name: this.databaseName
          }
        ];
        let index = 2;
        this.databaseNames.forEach(databaseNameList => {
          searchDatabaseName.push({
            nr: index,
            name: databaseNameList.name
          })
          index++;
        });
        this.databaseNames = [];
        this.databaseNames = searchDatabaseName;
        this.databaseName = '';
        this.updatePage();
      }, error => {
        if (error.error.body && error.error.body.detail) {
          this.messageService.add({
            severity: "error",
            summary: "HttpStatus: ",
            detail: error.error.body.detail,
          });
          this.databaseNameExist = true;
        }
      }
    )
    ;
  }
}
