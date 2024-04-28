import {StandardComponentSetUp} from "./Components";
import {ItemScheme} from "./Scheme";

export interface ItemSetUp{
  standardComponentSetUps:StandardComponentSetUp[];
  isNewScheme:boolean;
  scheme:string;
  itemName:string;
  itemTitle:string;
}

export interface BasicItem{
  itemScheme:ItemScheme;
}
