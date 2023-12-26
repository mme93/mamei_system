import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtAuthenticationResponse} from "../../model/Login";
import {environment} from "../../../../environments/environment";

export interface SudokuLevelList {
  mode: string;
  level: string[];
}

@Injectable({
  providedIn: 'root'
})
export class SudokuLevelService {

  private apiLoginUrl = environment.uri+':9000/api/sudoku/load/1/EASY';

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
