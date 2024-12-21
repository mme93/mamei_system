import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from 'rxjs';
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

  login(user:PixelQuestUserDto): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<PixelQuestAccountDto>(`${environment.uri}:9054/pixelquest/account/login`,user, httpOptions).pipe(
      map((response) => {
        this.accountSubject.next(response);
        return true;
      }),
      catchError((err) => {
        this.errorMsgService.showMessage(err.error.message);
        return of(false);
      })
    );
  }

 
}