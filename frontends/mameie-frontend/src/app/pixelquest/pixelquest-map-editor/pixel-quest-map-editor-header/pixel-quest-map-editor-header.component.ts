import { Component } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EditorConfigService } from 'src/app/service/editor/editor-config.service';

@Component({
  selector: 'app-pixel-quest-map-editor-header',
  standalone: true,
  imports: [],
  templateUrl: './pixel-quest-map-editor-header.component.html',
  styleUrl: './pixel-quest-map-editor-header.component.scss'
})
export class PixelQuestMapEditorHeaderComponent {
  showSettings=false;
  
  title: string = 'PixelQuest';
  gold: number = 0;          
  coins: number = 0;          
  units: number = 0;    

  constructor(private editorConfigService:EditorConfigService){
  }


  toggleSettings(){
    this.showSettings=!this.showSettings;
    this.editorConfigService.toggleShowSettings(this.showSettings);
  }

}
