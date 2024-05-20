import {Component, inject, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {SchemeService} from "../../../../../shared/services/dashboard/item/scheme/scheme.service";
import {StandardService} from "../../../../../shared/services/dashboard/component/standard/standard.service";
import {ComponentTableRow, StandardComponent} from "../../../../../shared/model/dashboard/Components";
import {MatChipInputEvent} from "@angular/material/chips";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {SchemeUiService} from "../../../../../shared/services/dashboard/item/scheme/scheme-ui.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CreateItemSetup} from "../../../../../shared/model/dashboard/Item";

@Component({
  selector: 'app-component-for-scheme',
  templateUrl: './component-for-scheme.component.html',
  styleUrls: ['./component-for-scheme.component.scss']
})
export class ComponentForSchemeComponent implements OnInit {
  selectedValueComponents = '';
  dataSource = new MatTableDataSource<ComponentTableRow>([]);

  createItemSetup: CreateItemSetup = {
    schemeName: '',
    schemes: [],
    components: [],
    componentTable: {
      componentTableRowNames: ['position', 'componentName', 'label', 'specification', 'defaultValue', 'valueList', 'open'],
      componentTableRow: []
    }
  }
  announcer = inject(LiveAnnouncer);

  scheme = new FormGroup({
    schemeName: new FormControl('', [Validators.required, Validators.minLength(1)]),
  })

  constructor(private schemeService: SchemeService,
              private schemeUiService: SchemeUiService,
              private standardService: StandardService) {
  }

  ngOnInit(): void {
    this.standardService.getAllStandardComponents().subscribe(result => this.createItemSetup.components = result);
    this.createItemSetup.schemes = this.schemeService.getAllScheme();
  }

  removeKeyword(keyword: string, valueLust: string[]) {
    const index = valueLust.indexOf(keyword);
    if (index >= 0) {
      valueLust.splice(index, 1);
      this.announcer.announce(`removed ${keyword}`);
    }
  }

  add(event: MatChipInputEvent, valueLust: string[]): void {
    const value = (event.value || '').trim();
    if (value) {
      valueLust.push(value);
    }
    event.chipInput!.clear();
  }

  addComponentToTable(selectedValueComponents: string) {
    const selectComponents: StandardComponent[] = this.createItemSetup.components.filter(component => component.value === selectedValueComponents)
    if (selectComponents.length === 1) {
      const selectComponent = selectComponents[0]
      this.createItemSetup.componentTable.componentTableRow.push({
        position: this.createItemSetup.componentTable.componentTableRow.length + 1,
        standardComponent: selectComponent,
        componentName: selectComponent.value,
        defaultValue: '',
        value: '',
        valueList: [],
        specification: selectComponent.specificationList[0],
        label: '',
        isBoolean: selectComponent.value === 'CHECKBOX',
        isMultiValue: ['RADIO_BUTTON'].includes(selectComponent.value)
      })
      this.dataSource = new MatTableDataSource<ComponentTableRow>(this.createItemSetup.componentTable.componentTableRow)
    }
  }

  getComponentForScheme() {
    return this.createItemSetup;
  }

  moveComponentUp(i: number) {
    this.schemeUiService.moveComponentUp(i, this.createItemSetup.componentTable.componentTableRow)
    this.dataSource = new MatTableDataSource<ComponentTableRow>(this.createItemSetup.componentTable.componentTableRow)
  }

  moveComponentDown(i: number) {
    this.dataSource = new MatTableDataSource<ComponentTableRow>(
      this.schemeUiService.moveComponentDown(i, this.createItemSetup.componentTable.componentTableRow))
  }

  deleteComponent(i: number) {
    this.schemeUiService.removeComponent(i, this.createItemSetup.componentTable.componentTableRow)
    this.dataSource = new MatTableDataSource<ComponentTableRow>(this.createItemSetup.componentTable.componentTableRow)
  }

  editComponent(i: number) {

  }
}
