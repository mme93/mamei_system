import {Injectable} from '@angular/core';

export interface SudokuLevelList {
  mode: string;
  level: string[];
}

@Injectable({
  providedIn: 'root'
})
export class SudokuLevelService {

  constructor() {
  }
}
