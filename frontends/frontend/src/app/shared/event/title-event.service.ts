import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TitleEventService {

  private titleUpdatedSource = new Subject<string>();

  titleUpdated$ = this.titleUpdatedSource.asObservable();

  updateTitle(newTitle: string): void {
    this.titleUpdatedSource.next(newTitle);
  }
}
