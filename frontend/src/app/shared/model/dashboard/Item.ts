import {ComponentTable, StandardComponent} from "./Components";
import {SchemeName} from "./Scheme";

export interface CreateItemSetup {
  schemeName: string;
  schemes: SchemeName[];
  components: StandardComponent[];
  componentTable: ComponentTable;
}
