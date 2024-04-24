import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { MatTableDataSource } from '@angular/material/table';

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
  valueTypes: string;
  description: string;
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
    {position: 1, name: 'label', valueTypes: 'STRING', description: 'XXX'},
    {position: 2, name: 'input_text', valueTypes: 'STRING', description: 'XXX'},
    {position: 3, name: 'input_text', valueTypes: 'NUMBER', description: 'XXX'},
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

  itemComponents: SelectedComponent[] = [
    {value: 'label', viewValue: 'Label'},
    {value: 'list', viewValue: 'List'},
    {value: 'input_text', viewValue: 'Text Field'},
    {value: 'input_number', viewValue: 'Number Field'},
    {value: 'input_date', viewValue: 'Date Field'},
    {value: 'input_text_area', viewValue: 'Text Area'},
  ];

  constructor(private eventService: TitleEventService) {
  }


  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Create Item');
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
      valueTypes: '',
      description: '',
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

