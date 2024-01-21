import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../shared/event/title-event.service";

@Component({
  selector: 'app-entities',
  templateUrl: './entities.component.html',
  styleUrls: ['./entities.component.scss']
})
export class EntitiesComponent implements OnInit{

  constructor(private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Entities')
  }
}
