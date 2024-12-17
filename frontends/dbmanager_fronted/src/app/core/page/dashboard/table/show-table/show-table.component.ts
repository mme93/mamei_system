import {Component, OnInit, WritableSignal} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {ActivatedRoute} from "@angular/router";
import {DatabaseMetaData, DatabaseTableView} from "../../../../../shared/model/table/TableView";
import {TableDialogService} from "../../../../../shared/service/ui/dialog/table/table-dialog.service";

@Component({
  selector: 'app-show-table',
  templateUrl: './show-table.component.html',
  styleUrl: './show-table.component.scss'
})
export class ShowTableComponent implements OnInit {
  isEdit = false;
  isTableDirty = false;
  databaseTableView: DatabaseTableView = {};
  tableName: string = '';
  databaseName: string = '';
  displayDialog: boolean = false;
  metaList: DatabaseMetaData[] = [];
  selectedMetaData: DatabaseMetaData = {};

  constructor(private tableService: TableService, private route: ActivatedRoute, private tableDialogService: TableDialogService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableName = params['tableName'];
      this.databaseName = params['databaseName'];
      const server = localStorage.getItem('server');
      if (server) {
        this.tableService.getTableByNameAndDatabase(server, this.databaseName, this.tableName)
          .subscribe(result => {
            result.metaData?.forEach(metaData => {
              this.metaList.push({name: metaData.field, code: metaData.field});
            })
            this.databaseTableView = result || {metaData: [], databaseTableRows: []};
          }, error => {
            this.databaseTableView = {metaData: [], databaseTableRows: []};
          });
      }
    });
  }

  openTableMetaDataDialog() {
    this.tableDialogService.showTableMetaDataDialog(this.tableName, this.databaseTableView.metaData);
  }
}
