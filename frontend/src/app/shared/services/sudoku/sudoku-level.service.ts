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

  private apiLoginUrl = 'http://localhost:9000/api/sudoku/load/1/EASY';

  constructor(private http: HttpClient) {
  }

  loadSudokuLevelList() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem('token') + '',
      }),
      withCredentials: true
    };
    this.http.post(this.apiLoginUrl, null, httpOptions).subscribe(result => console.log(result))
  }

}
