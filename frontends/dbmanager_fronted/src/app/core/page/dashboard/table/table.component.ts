import {Component} from '@angular/core';
import {DropdownSelectModel} from "../../../../shared/model/components/UiComponents";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent {
  databaseNames: DropdownSelectModel[] = [
    {name: "Alle", code: "ALLE"},
    {name: "mamei_system", code: "MAMEI_SYSTEM"},
    {name: "dummy", code: "Dummy"}
  ];
  selectedDatabaseName: DropdownSelectModel = {name: "Alle", code: "ALLE"};
  tableName='';

}
