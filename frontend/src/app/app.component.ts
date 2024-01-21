import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TitleEventService} from "./shared/event/title-event.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title: string = 'Default Title';

  isSidenavDisabled: boolean = true;

  constructor(private router: Router,private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.titleUpdated$.subscribe((newTitle) => {
      this.title = newTitle;
    });
  }

  logout() {
    localStorage.setItem('token', '')
    this.isSidenavDisabled = false;
    this.router.navigate(['/login'])
  }
}
