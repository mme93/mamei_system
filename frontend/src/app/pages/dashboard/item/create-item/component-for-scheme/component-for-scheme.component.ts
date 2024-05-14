import {Component, inject, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {ComponentTableRow} from "../../../../../shared/model/dashboard/Test";
import {SchemeService} from "../../../../../shared/services/dashboard/item/scheme/scheme.service";
import {StandardService} from "../../../../../shared/services/dashboard/component/standard/standard.service";
import {StandardComponent} from "../../../../../shared/model/dashboard/Components";
import {FormControl} from "@angular/forms";
import {MatChipInputEvent} from "@angular/material/chips";
import {LiveAnnouncer} from "@angular/cdk/a11y";

@Component({
  selector: 'app-component-for-scheme',
  templateUrl: './component-for-scheme.component.html',
  styleUrls: ['./component-for-scheme.component.scss']
})
export class ComponentForSchemeComponent implements OnInit {
  selectedValueComponents = '';
  dataSource = new MatTableDataSource<ComponentTableRow>([]);
  standardComponents: StandardComponent[] = []
  columns: string[] = ['position', 'componentName', 'label', 'specification', 'defaultValue', 'valueList'];
  componentTableRow: ComponentTableRow[] = [];

  keywords = ['angular', 'how-to', 'tutorial', 'accessibility'];
  formControl = new FormControl(['angular']);

  announcer = inject(LiveAnnouncer);

  constructor(private schemeService: SchemeService, private standardService: StandardService) {
  }

  ngOnInit(): void {
    this.standardService.getAllStandardComponents().subscribe(result => this.standardComponents = result);
  }

  removeKeyword(keyword: string) {
    const index = this.keywords.indexOf(keyword);
    if (index >= 0) {
      this.keywords.splice(index, 1);

      this.announcer.announce(`removed ${keyword}`);
    }
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our keyword
    if (value) {
      this.keywords.push(value);
    }

    // Clear the input value
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
        valueList: '',
        specification: selectComponent.specificationList[0],
        label: '',
        isBoolean: selectComponent.value === 'CHECKBOX',
        isMultiValue: ['RADIO_BUTTON'].includes(selectComponent.value)
      })
      this.dataSource = new MatTableDataSource<ComponentTableRow>(this.componentTableRow)
    }
  }
}
