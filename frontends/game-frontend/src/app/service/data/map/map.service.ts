import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PixelQuestMapDto, PixelQuestWorldDto } from '../../../pixelquest/model/test';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private mapSubject: BehaviorSubject<PixelQuestMapDto | null>;
  public map$: Observable<PixelQuestMapDto | null>;

  constructor(private http: HttpClient) {
    this.mapSubject = new BehaviorSubject<PixelQuestMapDto | null>(null);
    this.map$ = this.mapSubject.asObservable();
  }

  setCurrentMap(map: PixelQuestMapDto): void {
    this.mapSubject.next(map);
  }

}
