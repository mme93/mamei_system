import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PixelQuestAccountDto, PixelQuestUserDto } from 'src/app/model/account';
import { environment } from 'src/environments/environment';
import { ErrorMessageService } from '../../message/error-message.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountSubject: BehaviorSubject<PixelQuestAccountDto | null>;
  public account$: Observable<PixelQuestAccountDto | null>;

  constructor(private http: HttpClient,private errorMsgService:ErrorMessageService) {
    this.accountSubject = new BehaviorSubject<PixelQuestAccountDto | null>(null);
    this.account$ = this.accountSubject.asObservable();
  }

  getAccount(): PixelQuestAccountDto | null {
    return this.accountSubject.getValue();
  }

  login(user:PixelQuestUserDto){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post<PixelQuestAccountDto>(`${environment.uri}:9054/pixelquest/account/login`,user, httpOptions).subscribe({
      next: (result: PixelQuestAccountDto) => {
        this.accountSubject.next(result);
        console.log(result)
      },
      error: (error) => {
        const err = error as HttpErrorResponse
        this.errorMsgService.showMessage(err.error);
      }
    });
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
        const err = error as HttpErrorResponse
        this.errorMsgService.showMessage(err.error);
      }
    });
  }
}