import {Injectable, OnDestroy} from '@angular/core';
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {DatabaseMetaData, TableMetadata} from "../../../../model/table/TableView";
import {
  TableMetadataDialogComponent
} from "../../../../dialog/table/table-metadata-dialog/table-metadata-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class TableDialogService implements OnDestroy {

  ref: DynamicDialogRef | undefined;

  constructor(private dialogService: DialogService) {
  }

  showTableMetaDataDialog(tableName: string | null, metaData: TableMetadata[] | undefined) {
    this.ref = this.dialogService.open(TableMetadataDialogComponent, {
      header: 'Edit ' + tableName + ' Metadata',
      width: '70%',
      contentStyle: {"overflow": "auto"},
      baseZIndex: 10000,
      maximizable: true,
      closeOnEscape: true,
      data: {metaData: metaData}
    });
  }

  ngOnDestroy(): void {
    if (this.ref) {
      this.ref.close();
    }
  }
}
