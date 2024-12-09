import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
import { AppComponent } from '../../../app.component';

export interface Figure {
  img_path: string;
  row_pos: number;
  col_pos: number;
}

@Injectable({
  providedIn: 'root'
})
export class FigurecontrollerService{
  figure: Figure = {
    img_path: './assets/figure/figure.png',
    row_pos: 1,
    col_pos: 1,
  }

  private figureSubject: BehaviorSubject<Figure>;
  public figure$: Observable<Figure>;

  constructor(){
    this.figureSubject = new BehaviorSubject(this.figure);
    this.figure$ = this.figureSubject.asObservable();
  }


  public moveFigure(key: string): void {
    switch(key){
      case 'w':
        this.figure.row_pos--;
        this.figureSubject.next(this.figure);
        break;
      case 'a':
        this.figure.col_pos--;
        this.figureSubject.next(this.figure);
        break;
      case 's':
        this.figure.row_pos++;
        this.figureSubject.next(this.figure);
        break;
      case 'd':
        this.figure.col_pos++;
        this.figureSubject.next(this.figure);
        break;
    }
  }

}
