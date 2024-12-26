import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EditorButtonAction } from 'src/app/model/config';

@Injectable({
  providedIn: 'root'
})
export class EditorConfigService {

  private buttonActionSubject: BehaviorSubject<EditorButtonAction>;
  public buttonAction$: Observable<EditorButtonAction>;

  constructor() {
    this.buttonActionSubject = new BehaviorSubject({ mapSettings: false, mapColours: false } as EditorButtonAction);
    this.buttonAction$ = this.buttonActionSubject.asObservable();
  }

  toggleShowSettings(showSettings: boolean) {
    this.buttonActionSubject.next({ mapSettings: true, mapColours: false } as EditorButtonAction);
  }

  toggleShowColour() {
    this.buttonActionSubject.next({ mapSettings: false, mapColours: true } as EditorButtonAction);
  }
}
