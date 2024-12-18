import {DropdownSelectModel} from "../UiComponents";

export interface CreateMetaDataTableElement {
  nr?: number;
  isSelected?: boolean;
  field?: string;
  type?: DropdownSelectModel;
  typeInfo?: string;
  nullable?: DropdownSelectModel;
  key?: DropdownSelectModel;
  defaultValue?: string;
}

export interface CreateMetaDataTableElementUi {
  tableName?: string;
  databaseName?: string;
  keyDropDown: DropdownSelectModel[];
  nullableDropDown: DropdownSelectModel[];
  typeDropDown: DropdownSelectModel[];
  columnNames: string[];
  metaDataTableElements: CreateMetaDataTableElement[];
}
