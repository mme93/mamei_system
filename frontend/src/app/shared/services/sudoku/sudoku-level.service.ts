import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtAuthenticationResponse} from "../../model/Login";

export interface SudokuLevelList {
  mode: string;
  level: string[];
}

@Injectable({
  providedIn: 'root'
})
export class SudokuLevelService {

  private apiLoginUrl = 'http://localhost:9000/api/sudoku/load/levelList';

  constructor(private http: HttpClient) {
  }

  loadSudokuLevelList() {
    let headers = new HttpHeaders({
      'Authorization': localStorage.getItem('token')+''
    });
    this.http.post<JwtAuthenticationResponse>(this.apiLoginUrl, null,{headers}).subscribe(result =>console.log(result))
  }

}
