import {Component} from "./Components";

export interface SchemeName{
  schemeName:string;
  schemeViewName:string;
}

export interface ItemScheme{
  schemeName:string;
  schemeViewName:string;
  components:Component[];
}