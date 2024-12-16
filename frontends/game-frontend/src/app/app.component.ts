import { Component, HostListener } from '@angular/core';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
import { FigurecontrollerService } from './service/data/figure/figurecontroller.service';
import { ScreenSizeService } from './service/screen/screen-size.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'game-frontend';
  height: number = 0;
  private subscription!: Subscription;
  private keyBoardSubject: BehaviorSubject<string>;
  public keyBoard$: Observable<string>;

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (['w', 'a', 's', 'd'].includes(event.key)) {
      this.keyBoardSubject.next(event.key)
      this.figurecontrollerService.moveFigure(event.key);
    }
  }

  constructor(public figurecontrollerService: FigurecontrollerService, private screenSizeService: ScreenSizeService) {
    this.keyBoardSubject = new BehaviorSubject('init');
    this.keyBoard$ = this.keyBoardSubject.asObservable();

    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.height = (size.height * 0.05);
    });
  }

}
