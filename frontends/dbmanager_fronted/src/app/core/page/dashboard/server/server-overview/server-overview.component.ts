import {Component, OnInit} from '@angular/core';
import {ServerService} from "../../../../../shared/service/http/server/server.service";
import {TreeNode} from "primeng/api";
import {NodeConverterService} from "../../../../../shared/service/util/node-converter.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-server-overview',
  templateUrl: './server-overview.component.html',
  styleUrl: './server-overview.component.scss'
})
export class ServerOverviewComponent implements OnInit {
  tableAmounts = 0;
  databaseName = '';
  isTable = false;
  isSelected = false;
  tableName = '';
  serverName = localStorage.getItem('server');
  disabledTreeSelect = false;
  databaseTreeNodes: TreeNode[] = [];
  selectedDatabaseTreeNode: TreeNode = {label: '', children: []};

  constructor(private serverService: ServerService, private nodeConverterService: NodeConverterService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.serverService
      .getServerOverviewByName(localStorage.getItem('server'))
      .subscribe(result => this.databaseTreeNodes = this.nodeConverterService.convertAllToTreeNodes(result));
  }

  nodeSelect(event: any) {
    this.isSelected = true;
    if (!event.node.parent) {
      this.isTable = false;
      this.databaseName = event.node.label;
      this.tableAmounts = event.node.children.length;
    } else {
      this.isTable = true;
      this.databaseName = event.node.parent.label;
      this.tableName = event.node.label;
    }
  }

  nodeUnselect(event: any) {
    console.log(event.node.label)
  }

  openTable(databaseName:string,tableName: string) {
    this.router.navigate(['/dashboard/table/show',tableName,databaseName]);
  }
}
