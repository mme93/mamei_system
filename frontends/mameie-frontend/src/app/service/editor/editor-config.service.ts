import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditorConfigService {

  private showSettingsSubject: BehaviorSubject<boolean>;
  public showSettings$: Observable<boolean>;

  constructor() {
    this.showSettingsSubject = new BehaviorSubject(false);
    this.showSettings$ = this.showSettingsSubject.asObservable();
  }

  isShowSettings() {
    return this.showSettingsSubject.getValue();
  }

  toggleShowSettings(showSettings:boolean){
    this.showSettingsSubject.next(showSettings);
  }
}
