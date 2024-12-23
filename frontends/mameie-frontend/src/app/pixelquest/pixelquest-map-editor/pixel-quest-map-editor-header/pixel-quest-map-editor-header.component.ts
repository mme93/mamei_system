import { Component } from '@angular/core';

@Component({
  selector: 'app-pixel-quest-map-editor-header',
  standalone: true,
  imports: [],
  templateUrl: './pixel-quest-map-editor-header.component.html',
  styleUrl: './pixel-quest-map-editor-header.component.scss'
})
export class PixelQuestMapEditorHeaderComponent {

  title: string = 'PixelQuest';
  gold: number = 0;          
  coins: number = 0;          
  units: number = 0;    

}
