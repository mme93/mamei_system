import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PixelQuestAccountDto } from 'src/app/model/account';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountSubject: BehaviorSubject<PixelQuestAccountDto | null>;
  public account$: Observable<PixelQuestAccountDto | null>;

  constructor(private http: HttpClient) {
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
    this.http.get<PixelQuestAccountDto>(`${environment.uri}:9054/pixelquest/account/${account_id}`, httpOptions).subscribe({
      next: (result: PixelQuestAccountDto) => {
        this.accountSubject.next(result);
        console.log(result)
      },
      error: (error) => {
        console.error('Fehler beim Laden des Accounts:', error);
      }
    });
  }
}