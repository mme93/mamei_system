import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-pixelquest-header',
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './pixelquest-header.component.html',
  styleUrl: './pixelquest-header.component.scss'
})
export class PixelquestHeaderComponent {

  title: string = 'PixelQuest';
  gold: number = 0;          
  coins: number = 0;          
  units: number = 0;       

}
