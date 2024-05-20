import {Component, ViewChild} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {StandardService} from "../../../../shared/services/dashboard/component/standard/standard.service";
import {MatDialog} from "@angular/material/dialog";
import {SchemeService} from "../../../../shared/services/dashboard/item/scheme/scheme.service";
import {SchemeUiService} from "../../../../shared/services/dashboard/item/scheme/scheme-ui.service";
import {BasicItemService} from "../../../../shared/services/dashboard/item/basicitem/basic-item.service";
import {MatTableDataSource} from "@angular/material/table";
import {StandardComponentTable} from "../../../../shared/model/dashboard/Components";
import {ComponentTableRow} from "../../../../shared/model/dashboard/Test";
import {ComponentForSchemeComponent} from "../create-item/component-for-scheme/component-for-scheme.component";

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.scss']
})
export class EditItemComponent {

  @ViewChild(ComponentForSchemeComponent) schemeComponent!: ComponentForSchemeComponent;

  dataSource = new MatTableDataSource<ComponentTableRow>([]);

  columns: string[] = ['position','componentName','label','specification','defaultValue','valueList'];

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
