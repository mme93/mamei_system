import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TitleEventService} from "../../shared/event/title-event.service";

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss']
})
export class LandingpageComponent implements OnInit{
  gridItems = [
    { icon: 'feed', text: 'Entities',route:'/dashboard/entities/menu' },
    { icon: 'inventory', text: 'Item Create',route:'/dashboard/item/edit' },
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

  constructor(private router: Router,private eventService: TitleEventService) {}

  ngOnInit(): void {
    this.eventService.updateTitle('Welcome to Mamei Systems');
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
