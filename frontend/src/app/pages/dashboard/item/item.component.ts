import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {TitleEventService} from "../../../shared/event/title-event.service";
import {EntitiesService} from "../../../shared/services/dashboard/entities/entities.service";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent {

  gridItems = [
    { icon: 'feed', text: 'Entities',route:'/dashboard/entities/menu' },
    { icon: 'inventory', text: 'Task',route:'' },
    { icon: 'checklist', text: 'Checklist',route:'' },
    { icon: 'sports_esports', text: 'Games' ,route:''},
    { icon: 'settings', text: 'Settings' ,route:'/help/settings/board'},
    { icon: 'admin_panel_settings', text: 'Admin' ,route:'/admin/menu'},
    { icon: 'storage', text: 'Database Manager',route:'' },
    { icon: 'cloud_download', text: 'Data Cloud' ,route:''},
    { icon: 'insert_chart', text: 'Dashboard' ,route:''},
    { icon: 'email', text: 'Message' ,route:''},
    { icon: 'event', text: 'Calendar' ,route:''}
  ];

  constructor(private router: Router,private eventService: TitleEventService,private entityService:EntitiesService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Item')
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    console.log(filterValue)
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
