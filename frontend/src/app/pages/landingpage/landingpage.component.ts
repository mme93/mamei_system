import { Component } from '@angular/core';

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss']
})
export class LandingpageComponent {
  gridItems = [
    { icon: 'location_on', text: 'Location' },
    { icon: 'inventory', text: 'Task' },
    { icon: 'person', text: 'Person' },
    { icon: 'checklist', text: 'Checklist' }
  ];
}
