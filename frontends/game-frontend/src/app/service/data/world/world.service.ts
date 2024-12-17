import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PixelQuestWorldDto } from '../../../pixelquest/model/test';
import { MapService } from '../map/map.service';

@Injectable({
  providedIn: 'root'
})
export class WorldService {

  private worldSubject: BehaviorSubject<PixelQuestWorldDto | null>;
  public world$: Observable<PixelQuestWorldDto | null>;

  constructor(private http: HttpClient,private mapService:MapService) {
    this.worldSubject = new BehaviorSubject<PixelQuestWorldDto | null>(null);
    this.world$ = this.worldSubject.asObservable();
  }

  loadWorld(world_id: number, currentMapId: number, mapColIndex: number, mapRowIndex: number): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.http.get<PixelQuestWorldDto>(`http://217.160.26.246:9054/test/world/${world_id}`, httpOptions).subscribe({
      next: (result: PixelQuestWorldDto) => {
        result.maps[0].grid.rows[mapColIndex][mapRowIndex].hasPerson=true;
        this.worldSubject.next(result);
        this.mapService.setCurrentMap(result.maps[0]);
      },
      error: (error) => {
        console.error('Fehler beim Laden der Welt:', error);
      }
    });
  }
}
