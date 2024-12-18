import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../shared/event/title-event.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{
  constructor(private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard')
  }
}
