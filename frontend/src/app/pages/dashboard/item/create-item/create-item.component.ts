import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from '@angular/material/table';
import {StandardService} from "../../../../shared/services/dashboard/component/standard/standard.service";
import {StandardComponent, StandardComponentTable} from "../../../../shared/model/dashboard/Components";
import {ItemSetUp} from "../../../../shared/model/dashboard/Item";
import {MatDialog} from "@angular/material/dialog";
import {ComponentSettingDialogComponent} from "../../dialog/component-setting-dialog/component-setting-dialog.component";
import {SchemeName} from "../../../../shared/model/dashboard/Scheme";
import {SchemeService} from "../../../../shared/services/dashboard/item/scheme/scheme.service";
import {SchemeUiService} from "../../../../shared/services/dashboard/item/scheme/scheme-ui.service";

interface SelectedComponentScheme {
  value: string;
  viewValue: string;
}

export interface SchemeList {
  position: number;
  name: string;
  specification: string;
  description: string;
  styleClass: string[];
  spezificationList: string[];
}

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.scss']
})
export class CreateItemComponent implements OnInit {
  columns: string[] = ['position', 'viewValue', 'description', 'valueTypes', 'open'];
  showSchemeView: boolean = false;
  tableData: StandardComponentTable[] = [];
  counter = 1;
  group = 1;
  selectedValueComponents = '';
  selectedValueScheme = '';


  dataSource = new MatTableDataSource<StandardComponentTable>(this.tableData);

  scheme = new FormGroup({
    schemeName: new FormControl('', [Validators.required, Validators.minLength(1)]),
  })

  itemInformation = new FormGroup({
    itemName: new FormControl('', [Validators.required, Validators.minLength(1)]),
    itemTitle: new FormControl('', [Validators.required, Validators.minLength(1)])
  });


  schemeNames: SchemeName[] = [];

  standardComponents: StandardComponent[] = [];

  constructor(private eventService: TitleEventService,
              private standardService: StandardService,
              public dialog: MatDialog,
              private schemeService: SchemeService,
              private schemeUiService: SchemeUiService) {
  }


  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Create Item');
    this.standardService.getAllStandardComponents().subscribe(result => this.standardComponents = result);
    this.schemeNames = this.schemeService.getAllScheme();
    this.selectedValueScheme = this.schemeNames[0].schemeName;
  }

  addComponent() {
    let standardComponent = this.standardComponents.filter(standardComponent => standardComponent.value === this.selectedValueComponents)[0];
    this.tableData = this.schemeUiService.addTableData(this.tableData, standardComponent, this.counter, this.group);
    this.updateTable();
  }

  removeComponent(i: number) {
    this.schemeUiService.removeComponent(i, this.tableData);
    this.updateTable();
  }

  moveComponentUp(i: number) {
    this.schemeUiService.moveComponentUp(i, this.tableData);
    this.updateTable();
  }

  moveComponentDown(i: number) {
    this.schemeUiService.moveComponentDown(i, this.tableData);
    this.updateTable();
  }


  editComponent(i: number) {
    this.tableData[i]=this.schemeUiService.editComponent(this.tableData[i]);

  }

  updateTable() {
    this.counter = this.tableData.length + 1;
    this.dataSource = new MatTableDataSource<StandardComponentTable>(this.tableData);
  }

}

