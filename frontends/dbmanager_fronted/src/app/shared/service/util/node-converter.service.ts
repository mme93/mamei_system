import {Injectable} from '@angular/core';
import {TreeNode} from "primeng/api";
import {DatabaseOverview} from "../../model/server/database/Database";

@Injectable({
  providedIn: 'root'
})
export class NodeConverterService {

  constructor() {
  }

  convertToTreeNode(databaseOverview: DatabaseOverview): TreeNode {
    const treeNode: TreeNode = {
      label: databaseOverview.databaseName,
      children: databaseOverview.tableNames.map(tableName => ({
        label: tableName
      }))
    };
    return treeNode;
  }

  convertAllToTreeNodes(databaseOverviews: DatabaseOverview[]): TreeNode[] {
    let treeNodes: TreeNode[] = [];

    for (let i = 0; i < databaseOverviews.length; i++) {
      treeNodes.push(this.convertToTreeNode(databaseOverviews[i]));
    }
    return treeNodes;
  }
}
