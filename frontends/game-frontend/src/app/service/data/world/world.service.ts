import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PixelQuestWorldDto } from '../../../pixelquest/model/test';

@Injectable({
  providedIn: 'root'
})
export class WorldService {

  private worldSubject: BehaviorSubject<PixelQuestWorldDto | null>;
  public world$: Observable<PixelQuestWorldDto | null>;

  constructor(private http: HttpClient) {
    this.worldSubject = new BehaviorSubject<PixelQuestWorldDto | null>(null);
    this.world$ = this.worldSubject.asObservable();
  }

  loadWorld(world_id: number): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.http.get<PixelQuestWorldDto>(`http://localhost:9054/test/world/${world_id}`, httpOptions).subscribe({
      next: (result: PixelQuestWorldDto) => {
        console.log('Neue Welt geladen:', result);
        this.worldSubject.next(result);
      },
      error: (error) => {
        console.error('Fehler beim Laden der Welt:', error);
      }
    });
  }
}
