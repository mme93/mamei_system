export interface StandardComponent {
  id: number;
  viewValue: string;
  value: string;
  specificationList: string[];
  styleClassList: string[];
  description: string;
}

export interface StandardComponentSetUp {
  standardComponent: StandardComponent;
  content:String;
  group:number;
}
