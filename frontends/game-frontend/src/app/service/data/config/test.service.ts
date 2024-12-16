import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private testSubject: BehaviorSubject<String>;
  public test$: Observable<String>;

  constructor() {
    this.testSubject = new BehaviorSubject<String>('');
    this.test$ = this.testSubject.asObservable();
  }

  emitTest(test:String){
    console.warn(test)
    this.testSubject.next(test);
  }
}
