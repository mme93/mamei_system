import {Component, ViewChild} from '@angular/core';
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormBuilder, Validators} from "@angular/forms";
import {MatTableDataSource} from '@angular/material/table';
import {StandardService} from "../../../../shared/services/dashboard/component/standard/standard.service";
import {MatDialog} from "@angular/material/dialog";
import {SchemeService} from "../../../../shared/services/dashboard/item/scheme/scheme.service";
import {SchemeUiService} from "../../../../shared/services/dashboard/item/scheme/scheme-ui.service";
import {BasicItemService} from "../../../../shared/services/dashboard/item/basicitem/basic-item.service";
import {ComponentForSchemeComponent} from "./component-for-scheme/component-for-scheme.component";
import {ComponentTableRow} from "../../../../shared/model/dashboard/Components";


@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.scss']
})
export class CreateItemComponent {

  @ViewChild(ComponentForSchemeComponent) schemeComponent!: ComponentForSchemeComponent;

  dataSource = new MatTableDataSource<ComponentTableRow>([]);

  columns: string[] = ['position', 'componentName', 'label', 'specification', 'defaultValue', 'valueList'];

  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  isLinear = false;

  constructor(private _formBuilder: FormBuilder, private eventService: TitleEventService, private standardService: StandardService,
              public dialog: MatDialog, private schemeService: SchemeService, private schemeUiService: SchemeUiService,
              private basicItemService: BasicItemService) {
  }


  showValue() {
    console.log(this.schemeComponent.getComponentForScheme())
  }

}

