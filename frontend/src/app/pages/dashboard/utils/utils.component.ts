import { Component } from '@angular/core';

@Component({
  selector: 'app-utils',
  templateUrl: './utils.component.html',
  styleUrls: ['./utils.component.scss']
})
export class UtilsComponent {
  gridItems = [
    { icon: 'home', text: 'Home' },
    { icon: 'work', text: 'Work' },
    { icon: 'school', text: 'School' },
    { icon: 'directions_bike', text: 'Bike' },
    { icon: 'restaurant', text: 'Restaurant' },
    { icon: 'shopping_cart', text: 'Shopping' },
    { icon: 'movie', text: 'Movie' },
    { icon: 'music_note', text: 'Music' },
    { icon: 'sports', text: 'Sports' }
  ];
}
