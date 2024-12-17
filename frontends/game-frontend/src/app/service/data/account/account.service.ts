import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PixelQuestAccountDto, PixelQuestWorldDto } from '../../../pixelquest/model/test';
import { WorldService } from '../world/world.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountSubject: BehaviorSubject<PixelQuestAccountDto | null>;
  public account$: Observable<PixelQuestAccountDto | null>;

  constructor(private http: HttpClient,private worldService:WorldService) {
    this.accountSubject = new BehaviorSubject<PixelQuestAccountDto | null>(null);
    this.account$ = this.accountSubject.asObservable();
  }

  getAccount(): PixelQuestAccountDto | null {
    return this.accountSubject.getValue();
  }

  loadAccount(account_id: number): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.get<PixelQuestAccountDto>(`http://217.160.26.246:9054/test/accout/${account_id}`, httpOptions).subscribe({
      next: (result: PixelQuestAccountDto) => {
        this.accountSubject.next(result);
        this.worldService.loadWorld(result.currentWorldId,result.currentMapId,result.mapColIndex,result.mapRowIndex);
      },
      error: (error) => {
        console.error('Fehler beim Laden des Accounts:', error);
      }
    });
  }
}
