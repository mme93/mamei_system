import {Component, inject, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {ComponentTableRow} from "../../../../../shared/model/dashboard/Test";
import {SchemeService} from "../../../../../shared/services/dashboard/item/scheme/scheme.service";
import {StandardService} from "../../../../../shared/services/dashboard/component/standard/standard.service";
import {StandardComponent} from "../../../../../shared/model/dashboard/Components";
import {MatChipInputEvent} from "@angular/material/chips";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {SchemeUiService} from "../../../../../shared/services/dashboard/item/scheme/scheme-ui.service";

@Component({
  selector: 'app-component-for-scheme',
  templateUrl: './component-for-scheme.component.html',
  styleUrls: ['./component-for-scheme.component.scss']
})
export class ComponentForSchemeComponent implements OnInit {
  selectedValueComponents = '';
  dataSource = new MatTableDataSource<ComponentTableRow>([]);
  standardComponents: StandardComponent[] = []
  columns: string[] = ['position', 'componentName', 'label', 'specification', 'defaultValue', 'valueList','open'];
  componentTableRow: ComponentTableRow[] = [];

  announcer = inject(LiveAnnouncer);

  constructor(private schemeService: SchemeService,
              private schemeUiService: SchemeUiService,
              private standardService: StandardService) {
  }

  ngOnInit(): void {
    this.standardService.getAllStandardComponents().subscribe(result => this.standardComponents = result);
  }

  removeKeyword(keyword: string,valueLust:string[]) {
    const index = valueLust.indexOf(keyword);
    if (index >= 0) {
      valueLust.splice(index, 1);
      this.announcer.announce(`removed ${keyword}`);
    }
  }

  add(event: MatChipInputEvent,valueLust:string[]): void {
    const value = (event.value || '').trim();
    if (value) {
      valueLust.push(value);
    }
    event.chipInput!.clear();
  }

  addComponentToTable(selectedValueComponents: string) {
    const selectComponents: StandardComponent[] = this.standardComponents.filter(component => component.value === selectedValueComponents)
    if (selectComponents.length === 1) {
      const selectComponent = selectComponents[0]
      this.componentTableRow.push({
        position: this.componentTableRow.length + 1,
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
      this.dataSource = new MatTableDataSource<ComponentTableRow>(this.componentTableRow)
    }
  }

  getComponentForScheme(){
    return this.componentTableRow;
  }

  moveComponent(i:number) {

  }

  deleteComponent(i:number) {
    //this.schemeUiService.
    this.dataSource = new MatTableDataSource<ComponentTableRow>(this.componentTableRow)
  }

  editComponent(i:number) {

  }
}
