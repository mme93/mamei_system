import {Injectable} from '@angular/core';
import {CreateMetaDataTableElementUi} from "../../../../model/components/table/UiTables";

@Injectable({
  providedIn: 'root'
})
export class CreateTableUiService {

  constructor() {
  }

  init(tableName: string, databaseName: string): CreateMetaDataTableElementUi {
    return {
      tableName: tableName,
      databaseName: databaseName,
      keyDropDown: [
        {name: ' - ', code: 'EMPTY'},
        {name: 'PRIMARY KEY', code: 'PRIMARY_KEY'},
        {name: 'PK AUTO INCREMENT', code: 'AUTO_INCREMENT'},
        {name: 'FOREIGN KEY', code: 'FOREIGN_KEY'},
        {name: 'UNIQUE', code: 'UNIQUE'},
      ],
      nullableDropDown: [
        {name: 'Yes', code: 'YES'},
        {name: 'No', code: 'NO'}
      ],
      typeDropDown: [
        {name: 'VARCHAR', code: 'VARCHAR'},
        {name: 'CHAR', code: 'CHAR'},
        {name: 'INT', code: 'INT'},
        {name: 'BINARY', code: 'BINARY'},
        {name: 'VARBINARY', code: 'VARBINARY'},
        {name: 'TINYBLOB', code: 'TINYBLOB'},
        {name: 'TINYTEXT', code: 'TINYTEXT'},
        {name: 'TEXT', code: 'TEXT'},
        {name: 'BLOB', code: 'BLOB'},
        {name: 'MEDIUMTEXT', code: 'MEDIUMTEXT'},
        {name: 'MEDIUMBLOB', code: 'MEDIUMBLOB'},
        {name: 'LONGTEXT', code: 'LONGTEXT'},
        {name: 'LONGBLOB', code: 'LONGBLOB'},
        {name: 'ENUM', code: 'ENUM'},
        {name: 'VARCHAR', code: 'VARCHAR'},
        {name: 'SET', code: 'SET'},
        {name: 'BIT', code: 'BIT'},
        {name: 'TINYINT', code: 'TINYINT'},
        {name: 'BOOL', code: 'BOOL'},
        {name: 'SMALLINT', code: 'SMALLINT'},
        {name: 'MEDIUMINT', code: 'MEDIUMINT'},
        {name: 'BIGINT', code: 'BIGINT'},
        {name: 'FLOAT', code: 'FLOAT'},
        {name: 'DOUBLE', code: 'DOUBLE'},
        {name: 'DECIMAL', code: 'DECIMAL'},
        {name: 'DATE', code: 'DATE'},
        {name: 'DATETIME', code: 'DATETIME'},
        {name: 'TIMESTAMP', code: 'TIMESTAMP'},
        {name: 'TIME', code: 'TIME'},
        {name: 'YEAR', code: 'YEAR'},
      ],
      columnNames: ['Nr', 'Selected', 'Name', 'Type', 'Type Size', 'Is nullable', 'Key', 'Default Value'],
      metaDataTableElements: []
    }
  }
}
