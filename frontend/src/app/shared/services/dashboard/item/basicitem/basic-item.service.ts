import { Injectable } from '@angular/core';
import {BasicItem} from "../../../../model/dashboard/Item";

@Injectable({
  providedIn: 'root'
})
export class BasicItemService {

  constructor() { }

  updateBasicItem(basicItem:BasicItem){

    return basicItem;
  }

  init() :BasicItem{
    return {
      itemScheme:{
        schemeName:'',
        schemeViewName:'',
        components:[]
      }
    };
  }
}
