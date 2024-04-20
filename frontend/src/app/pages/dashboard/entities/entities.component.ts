import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../shared/event/title-event.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-entities',
  templateUrl: './entities.component.html',
  styleUrls: ['./entities.component.scss']
})
export class EntitiesComponent implements OnInit{

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

  constructor(private router: Router,private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Dashboard - Entities')
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

}
