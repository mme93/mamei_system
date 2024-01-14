import { Component } from '@angular/core';

@Component({
  selector: 'app-utils',
  templateUrl: './utils.component.html',
  styleUrls: ['./utils.component.scss']
})
export class UtilsComponent {
  coppyList= [
    { icon: 'location_on', text: 'Location' },
    { icon: 'inventory', text: 'Task' },
    { icon: 'person', text: 'Person' },
    { icon: 'checklist', text: 'Checklist' }
  ];
  gridItems = [
    { icon: 'location_on', text: 'Location' },
    { icon: 'inventory', text: 'Task' },
    { icon: 'person', text: 'Person' },
    { icon: 'checklist', text: 'Checklist' }
  ];

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;

    this.gridItems = this.coppyList.filter(item =>
      {
        console.log(item.text.toLowerCase()+" | "+filterValue)
        return item.text.toLowerCase().includes(filterValue);
      }
    );
  }
}
