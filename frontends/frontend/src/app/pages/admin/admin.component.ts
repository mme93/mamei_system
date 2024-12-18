import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../shared/event/title-event.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit{
  constructor(private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Admin Area')
  }
}
