import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private http: HttpClient) {
  }

  getMapEntity(map_id:number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token') + '',
        'Content-Type': 'application/json'
      })
    };
    const url='http://localhost:9054/test/world/'+map_id;
    this.http.get(url, httpOptions).subscribe(result => console.log(result));
  }

}
