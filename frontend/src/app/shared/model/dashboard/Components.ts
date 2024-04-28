export interface StandardComponent {
  id: number;
  viewValue: string;
  value: string;
  specificationList: string[];
  styleClassList: string[];
  description: string;
}

export interface StandardComponentTable {
  position:number;
  standardComponent: StandardComponent;
  content:String;
  group:number;
  standardComponentSettings:StandardComponentSettings;
}

export interface StandardComponentSettings{
  specification:string;
  styleClass:string;
  subContent:string[];
  errorMsg:string;
}

export interface Component{
  position:number;
  content:String;
  group:number;
  standardComponentSettings:StandardComponentSettings;
}

