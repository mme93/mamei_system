import { Component } from '@angular/core';

@Component({
  selector: 'app-utils',
  templateUrl: './utils.component.html',
  styleUrls: ['./utils.component.scss']
})
export class UtilsComponent {
  coppyList= [
    { icon: 'home', text: 'Home' },
    { icon: 'work', text: 'Work' },
    { icon: 'school', text: 'School' },
    { icon: 'directions_bike', text: 'Bike' },
    { icon: 'restaurant', text: 'Restaurant' },
    { icon: 'shopping_cart', text: 'Shopping' },
    { icon: 'movie', text: 'Movie' },
    { icon: 'sports', text: 'Sports' },
    { icon: 'music_note', text: 'Music' }
  ];
  gridItems = [
    { icon: 'home', text: 'Home' },
    { icon: 'work', text: 'Work' },
    { icon: 'school', text: 'School' },
    { icon: 'directions_bike', text: 'Bike' },
    { icon: 'restaurant', text: 'Restaurant' },
    { icon: 'shopping_cart', text: 'Shopping' },
    { icon: 'movie', text: 'Movie' },
    { icon: 'sports', text: 'Sports' },
    { icon: 'music_note', text: 'Music' }
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
