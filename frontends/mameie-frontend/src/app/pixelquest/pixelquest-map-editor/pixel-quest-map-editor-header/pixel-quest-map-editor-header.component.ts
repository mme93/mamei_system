import { Component } from '@angular/core';
import { DialogService, DynamicDialogModule, DynamicDialogRef } from 'primeng/dynamicdialog';
import { EditorConfigService } from 'src/app/service/editor/editor-config.service';
import { PixelquestMapEditorSettingsComponent } from '../dialogs/pixelquest-map-editor-settings/pixelquest-map-editor-settings.component';
import { NewMapSettings } from 'src/app/model/config';

@Component({
  selector: 'app-pixel-quest-map-editor-header',
  standalone: true,
  imports: [DynamicDialogModule, PixelquestMapEditorSettingsComponent],
  providers: [DialogService],
  templateUrl: './pixel-quest-map-editor-header.component.html',
  styleUrl: './pixel-quest-map-editor-header.component.scss'
})
export class PixelQuestMapEditorHeaderComponent {

  title: string = 'PixelQuest Editor';
  gold: number = 0;
  coins: number = 0;
  units: number = 0;

  constructor(private editorConfigService: EditorConfigService) {
  }


  toggleSettings() {
    this.editorConfigService.toggleShowSettings(true);
  }

}
