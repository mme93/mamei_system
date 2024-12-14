export interface WorldGridRow {
    gridElements: WorldGridElement[];
  }
  
  export interface WorldGridElement {
    rowSize: number;
    colSize: number;
    rowIndex: number;
    colIndex: number;
    backgroundImg: string;
    hasPerson: boolean;
  }