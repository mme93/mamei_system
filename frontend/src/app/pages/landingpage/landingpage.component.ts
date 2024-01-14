import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss']
})
export class LandingpageComponent {
  gridItems = [
    { icon: 'feed', text: 'Entities',route:'' },
    { icon: 'inventory', text: 'Task',route:'' },
    { icon: 'checklist', text: 'Checklist',route:'' },
    { icon: 'sports_esports', text: 'Games' ,route:''},
    { icon: 'settings', text: 'Settings' ,route:'/help/settings/board'},
    { icon: 'storage', text: 'Database Manager',route:'' },
    { icon: 'cloud_download', text: 'Data Cloud' ,route:''},
    { icon: 'insert_chart', text: 'Dashboard' ,route:''},
    { icon: 'email', text: 'Message' ,route:''},
    { icon: 'event', text: 'Calendar' ,route:''}

  ];

  constructor(private router: Router) {}

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
