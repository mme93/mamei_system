import {AfterContentInit, AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TitleEventService} from "./shared/event/title-event.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit  {

  title: string = 'Default Title';

  isSidenavDisabled: boolean = true;

  constructor(private router: Router,private eventService: TitleEventService,private cdRef: ChangeDetectorRef ) {
  }

  ngAfterViewInit  (): void {
    this.eventService.titleUpdated$.subscribe((newTitle) => {
      this.title = newTitle;
      this.cdRef.detectChanges();
    });
  }

  logout() {
    localStorage.setItem('token', '')
    this.isSidenavDisabled = false;
    this.router.navigate(['/login'])
  }

  openAccount() {
    this.router.navigate(['/help/settings/account'])
  }
}
