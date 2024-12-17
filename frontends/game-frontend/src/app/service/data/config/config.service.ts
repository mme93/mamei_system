import { Injectable } from '@angular/core';
import { PixelQuestComponentView } from '../../../pixelquest/model/test';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private componentViewSubject: BehaviorSubject<{ isWorld: boolean, isLogin: boolean }>;
  public componentView$: Observable<{ isWorld: boolean, isLogin: boolean }>;

  constructor() {
    this.componentViewSubject = new BehaviorSubject<PixelQuestComponentView>({ isWorld: false, isLogin: true });
    this.componentView$ = this.componentViewSubject.asObservable();
  }

  emitView(view:PixelQuestComponentView){
    this.componentViewSubject.next(view);
  }
}
