import { Component } from '@angular/core';
import { SplitButtonModule } from 'primeng/splitbutton';

@Component({
  selector: 'app-pixelquest-header',
  standalone: true,
  imports: [SplitButtonModule],
  templateUrl: './pixelquest-header.component.html',
  styleUrl: './pixelquest-header.component.scss'
})
export class PixelquestHeaderComponent {

  title: string = 'PixelQuest';
  gold: number = 0;          
  coins: number = 0;          
  units: number = 0;       
  items = [
    {
      label: 'File',
      items: [
        { label: 'Open', icon: 'pi pi-fw pi-download' },
        { separator: true },
        { label: 'Quit', icon: 'pi pi-fw pi-times' }
      ]
    }
  ]  
}
