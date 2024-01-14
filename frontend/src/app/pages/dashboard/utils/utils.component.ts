import { Component } from '@angular/core';

@Component({
  selector: 'app-utils',
  templateUrl: './utils.component.html',
  styleUrls: ['./utils.component.scss']
})
export class UtilsComponent {
  copyList= [
    { icon: 'location_on', text: 'Overview' },
    { icon: 'inventory', text: 'Task' },
    { icon: 'checklist', text: 'Checklist' }
  ];
  gridItems = [
    { icon: 'location_on', text: 'Overview' },
    { icon: 'inventory', text: 'Task' },
    { icon: 'checklist', text: 'Checklist' }
  ];

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;

    this.gridItems = this.copyList.filter(item =>
      {
        return item.text.toLowerCase().includes(filterValue.toLowerCase());
      }
    );
  }
}
