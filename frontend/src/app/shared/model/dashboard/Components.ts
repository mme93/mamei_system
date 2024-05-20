export interface StandardComponent {
  id: number;
  viewValue: string;
  value: string;
  specificationList: string[];
  styleClassList: string[];
  description: string;
}

export interface ComponentTable{
  componentTableRowNames:String[];
  componentTableRow:ComponentTableRow[];
}
export interface ComponentTableRow{
  position:number;
  standardComponent: StandardComponent;
  componentName:String;
  defaultValue:String;
  value:String;
  valueList:String[];
  specification:String;
  label:String;
  isBoolean:boolean;
  isMultiValue:boolean;
}
