import { Injectable } from '@angular/core';
import {SchemeName} from "../../../../model/dashboard/Scheme";

@Injectable({
  providedIn: 'root'
})
export class SchemeService {

  constructor() { }

  getAllScheme():SchemeName[]{
    return [
      {schemeName:'new_scheme',schemeViewName:'New Scheme'},
      {schemeName:'ADDRESS_SCHEME',schemeViewName:'Address Scheme'},
    ]
  }




}
