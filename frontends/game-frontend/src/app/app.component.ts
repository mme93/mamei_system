import { Component, HostListener } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { FigurecontrollerService } from './service/data/figure/figurecontroller.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'game-frontend';

  private keyBoardSubject: BehaviorSubject<string>;
  public keyBoard$: Observable<string>;

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) { 
    if(['w', 'a', 's', 'd'].includes(event.key)){
      this.keyBoardSubject.next(event.key)
      this.figurecontrollerService.moveFigure(event.key);
    }
  }

  constructor(public figurecontrollerService:FigurecontrollerService){
    this.keyBoardSubject = new BehaviorSubject('init');
    this.keyBoard$ = this.keyBoardSubject.asObservable();
  }

}
