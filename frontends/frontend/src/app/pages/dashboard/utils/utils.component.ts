import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../shared/event/title-event.service";

@Component({
  selector: 'app-utils',
  templateUrl: './utils.component.html',
  styleUrls: ['./utils.component.scss']
})
export class UtilsComponent implements OnInit {

  copyList = [
    {icon: 'location_on', text: 'Overview'},
    {icon: 'inventory', text: 'Task'},
    {icon: 'checklist', text: 'Checklist'}
  ];
  gridItems = [
    {icon: 'location_on', text: 'Overview'},
    {icon: 'inventory', text: 'Task'},
    {icon: 'checklist', text: 'Checklist'}
  ];

  constructor(private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Utils')
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;

    this.gridItems = this.copyList.filter(item => {
        return item.text.toLowerCase().includes(filterValue.toLowerCase());
      }
    );
  }
}
