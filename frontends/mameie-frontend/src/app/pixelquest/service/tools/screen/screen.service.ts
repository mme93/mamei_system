import { Injectable, NgZone } from '@angular/core';
import { BehaviorSubject, Observable, fromEvent } from 'rxjs';
import { map, startWith, distinctUntilChanged } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ScreenService {

  private screenSizeSubject: BehaviorSubject<{ width: number, height: number }>;
  public screenSize$: Observable<{ width: number, height: number }>;

  constructor(private ngZone: NgZone) {
    const initialSize = { width: window.innerWidth, height: window.innerHeight };

    this.screenSizeSubject = new BehaviorSubject(initialSize);
    this.screenSize$ = this.screenSizeSubject.asObservable();


    this.initScreenSizeListener();
  }

  private initScreenSizeListener(): void {
    fromEvent(window, 'resize')
      .pipe(
        map(() => ({
          width: window.innerWidth,
          height: window.innerHeight
        })),
        distinctUntilChanged((prev, curr) => 
          prev.width === curr.width && prev.height === curr.height
        )
      )
      .subscribe(size => {
        this.ngZone.run(() => this.screenSizeSubject.next(size));
      });
  }
}
