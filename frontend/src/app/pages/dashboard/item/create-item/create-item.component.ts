import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from '@angular/material/table';
import {StandardService} from "../../../../shared/services/dashboard/component/standard/standard.service";
import {StandardComponent, StandardComponentSetUp} from "../../../../shared/model/dashboard/Components";
import {ItemSetUp} from "../../../../shared/model/dashboard/Item";
import {MatDialog} from "@angular/material/dialog";
import {ComponentSettingDialogComponent} from "../../dialog/component-setting-dialog/component-setting-dialog.component";
import {SchemeName} from "../../../../shared/model/dashboard/Scheme";
import {SchemeService} from "../../../../shared/services/dashboard/item/scheme/scheme.service";

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
  counter = 1;
  group = 1;
  selectedValueComponents = '';
  selectedValueScheme = '';

  itemSetup: ItemSetUp = {
    standardComponentSetUps: [],
    isNewScheme: true,
    scheme: 'New Scheme',
    itemName: '',
    itemTitle: ''
  }

  dataSource = new MatTableDataSource<StandardComponentSetUp>(this.itemSetup.standardComponentSetUps);

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
              private schemeService:SchemeService) {
  }


  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Create Item');
    this.standardService.getAllStandardComponents().subscribe(result => this.standardComponents = result);
    this.schemeNames=this.schemeService.getAllScheme();
    this.selectedValueScheme=this.schemeNames[0].schemeName;
  }

  addComponent() {
    let standardComponent=this.standardComponents.filter(standardComponent => standardComponent.value===this.selectedValueComponents)[0];
    this.itemSetup.standardComponentSetUps.push({
      position: this.counter,
      standardComponent: standardComponent,
      content:'',
      group:this.group,
      standardComponentSettings:{
        specification:standardComponent.specificationList[0],
        styleClass:standardComponent.styleClassList[0],
        subContent:[],
        errorMsg:''
      },
    });
    this.counter++;
    this.dataSource = new MatTableDataSource<StandardComponentSetUp>(this.itemSetup.standardComponentSetUps);
  }

  removeComponent(i: number) {
    this.itemSetup.standardComponentSetUps.splice(i, 1);
    this.updatePosition();
  }

  moveComponentUp(i: number) {
    let temp = this.itemSetup.standardComponentSetUps[i];
    this.itemSetup.standardComponentSetUps[i] = this.itemSetup.standardComponentSetUps[i - 1];
    this.itemSetup.standardComponentSetUps[i - 1] = temp;
    this.updatePosition();
  }

  moveComponentDown(i: number) {
    let temp = this.itemSetup.standardComponentSetUps[i];
    this.itemSetup.standardComponentSetUps[i] = this.itemSetup.standardComponentSetUps[i + 1];
    this.itemSetup.standardComponentSetUps[i + 1] = temp;
    this.updatePosition();
  }

  updatePosition() {
    for (let i = 0; i < this.itemSetup.standardComponentSetUps.length; i++) {
      this.itemSetup.standardComponentSetUps[i].position = i + 1;
    }
    this.dataSource = new MatTableDataSource<StandardComponentSetUp>(this.itemSetup.standardComponentSetUps);
    this.counter = this.itemSetup.standardComponentSetUps.length;
  }

  editComponent(i: number) {
    let standardComponentSetUp = this.itemSetup.standardComponentSetUps[i];
    let dialogRef = this.dialog.open(ComponentSettingDialogComponent, {
      height: '400px',
      width: '600px',
      data: {standardComponentSetUp: standardComponentSetUp}
    });
    dialogRef.afterClosed().subscribe((result:StandardComponentSetUp) => {
      if (result) {
        this.itemSetup.standardComponentSetUps[i]=result;
      }
    });
  }

}

