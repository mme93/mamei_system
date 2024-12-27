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
    this.buttonActionSubject = new BehaviorSubject({ buttonTyp: 'field',imageType:'empty' } as EditorButtonAction);
    this.buttonAction$ = this.buttonActionSubject.asObservable();
  }

  toggleShowSettings() {
    let buttonAction: EditorButtonAction = this.buttonActionSubject.getValue();
    buttonAction.buttonTyp = 'settings';
    this.buttonActionSubject.next(buttonAction);
  }

  toggleShowColour() {
    let buttonAction: EditorButtonAction = this.buttonActionSubject.getValue();
    buttonAction.buttonTyp = 'color';
    this.buttonActionSubject.next(buttonAction);
  }

  toggleImages(isField: boolean, isObject: boolean) {
    let buttonAction: EditorButtonAction = this.buttonActionSubject.getValue();
    buttonAction.buttonTyp = 'image';
    if (isField && !isObject) {
      buttonAction.imageType = 'field';
    } else if (!isField && isObject) {
      buttonAction.imageType = 'object';
    }else{
      buttonAction.imageType = 'empty';
    }
    this.buttonActionSubject.next(buttonAction);
  }
}
