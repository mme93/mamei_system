import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { MatTableDataSource } from '@angular/material/table';
import {StandardService} from "../../../../shared/services/dashboard/component/standard/standard.service";
import {StandardComponent} from "../../../../shared/model/dashboard/Components";

interface SelectedComponent {
  value: string;
  viewValue: string;
}

interface SelectedComponentScheme {
  value: string;
  viewValue: string;
}

interface SelectedComponentValueTyp {
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
  columns: string[] = ['position', 'name', 'valueTypes', 'description','open'];
  showSchemeView: boolean = false;
  counter = 3;
  selectedValueComponents = '';
  selectedValueScheme = '';
  schemeList: SchemeList[] = [
    {position: 1, name: 'label', specification: 'STRING', description: 'Label',styleClass:['default'],spezificationList:['default']},
    {position: 2, name: 'input_text', specification: 'STRING', description: 'Input Text Type String',styleClass:['default'],spezificationList:['default']},
    {position: 3, name: 'input_text', specification: 'NUMBER', description: 'Input Text Type Number',styleClass:['default'],spezificationList:['default']},
    {position: 4, name: 'input_text_area', specification: 'STRING', description: 'Input Text Area',styleClass:['default'],spezificationList:['default']},
    {position: 5, name: 'list_checkbox', specification: 'boolean', description: 'List with Checkbox',styleClass:['default'],spezificationList:['default']},
    {position: 6, name: 'checkbox', specification: 'BOOLEAN', description: 'Checkbox',styleClass:['default'],spezificationList:['default']},
    {position: 7, name: 'checkbox_with_sub_checks', specification: 'BOOLEAN', description: 'Checkbox with Subchecks',styleClass:['default'],spezificationList:['default']},
    {position: 8, name: 'radio_button', specification: 'BOOLEAN', description: 'Radion Buttons',styleClass:['default'],spezificationList:['default']},
  ];
  dataSource =  new MatTableDataSource<SchemeList>(this.schemeList);

  componentScheme = new FormGroup({
    number: new FormControl('', [Validators.required, Validators.minLength(1)]),
    text: new FormControl('', [Validators.required, Validators.minLength(1)]),
    text_area: new FormControl('', [Validators.required, Validators.minLength(1)]),
  })


  scheme = new FormGroup({
    schemeName: new FormControl('', [Validators.required, Validators.minLength(1)]),
  })

  itemInformation = new FormGroup({
    itemName: new FormControl('', [Validators.required, Validators.minLength(1)]),
    itemTitle: new FormControl('', [Validators.required, Validators.minLength(1)])
  });

  valueTypes: SelectedComponentValueTyp[] = [
    {value: 'BOOLEAN', viewValue: 'boolean'},
    {value: 'STRING', viewValue: 'String'},
    {value: 'NUMBER', viewValue: 'number'}
  ];

  itemScheme: SelectedComponentScheme[] = [
    {value: 'new_scheme', viewValue: 'New Scheme'},
    {value: 'todo_scheme', viewValue: 'ToDo List'}
  ];

  standardComponents:StandardComponent[]=[];

  constructor(private eventService: TitleEventService, private standardService:StandardService) {
  }


  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Create Item');
    this.standardService.getAllStandardComponents().subscribe(result => this.standardComponents=result);
  }

  getErrorMessage() {
    if (this.itemInformation.controls.itemName.hasError('required')) {
      return 'You must enter a value';
    }
    return this.itemInformation.controls.itemName.hasError('username') ? 'Not a valid username' : '';
  }

  getItemTitleErrorMessage() {
    if (this.itemInformation.controls.itemTitle.hasError('required')) {
      return 'You must enter a value';
    }
    return this.itemInformation.controls.itemTitle.hasError('password') ? 'Not a valid password' : '';
  }


  addComponent() {
    this.schemeList.push({
      position:this.counter,
      name: this.selectedValueComponents,
      specification: '',
      spezificationList:['default'],
      description: '',
      styleClass:['default']
    });
    this.counter++;
    this.dataSource = new MatTableDataSource<SchemeList>(this.schemeList);
  }

  removeComponent(i:number) {
    this.schemeList.splice(i,1);
    this.updatePosition();
  }

  moveComponentUp(i:number) {
    let temp =  this.schemeList[i];
    this.schemeList[i] =  this.schemeList[i-1];
    this.schemeList[i-1] = temp;
    this.updatePosition();
  }

  moveComponentDown(i:number) {
    let temp =  this.schemeList[i];
    this.schemeList[i] =  this.schemeList[i+1];
    this.schemeList[i+1] = temp;
    this.updatePosition();
  }

  updatePosition(){
    for(let i=0;i<this.schemeList.length;i++){
      this.schemeList[i].position=i+1;
    }
    this.dataSource = new MatTableDataSource<SchemeList>(this.schemeList);
    this.counter=this.schemeList.length;
  }

}

