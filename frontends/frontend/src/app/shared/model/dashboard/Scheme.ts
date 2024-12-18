import {ComponentTable, StandardComponent} from "./Components";

export interface SchemeName{
  schemeName:string;
  schemeViewName:string;
}

export interface CreateSchemeSetup {
  schemeName: string;
  schemes: SchemeName[];
  components: StandardComponent[];
  componentTable: ComponentTable;
}

