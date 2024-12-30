import { Component } from '@angular/core';
import { DialogService, DynamicDialogModule, DynamicDialogRef } from 'primeng/dynamicdialog';
import { PixelquestMapEditorSettingsComponent } from '../dialogs/pixelquest-map-editor-settings/pixelquest-map-editor-settings.component';

import { CommonModule } from '@angular/common';
import { EditorConfigService } from '../../service/editor/editor-config.service';

@Component({
  selector: 'app-pixel-quest-map-editor-header',
  standalone: true,
  imports: [DynamicDialogModule, PixelquestMapEditorSettingsComponent,CommonModule],
  providers: [DialogService],
  templateUrl: './pixel-quest-map-editor-header.component.html',
  styleUrl: './pixel-quest-map-editor-header.component.scss'
})
export class PixelQuestMapEditorHeaderComponent {

  isField: boolean = false;
  isObject: boolean = false;
  title: string = 'PixelQuest Editor';
  gold: number = 0;
  coins: number = 0;
  units: number = 0;

  constructor(private editorConfigService: EditorConfigService) {
  }


  toggleSettings() {
    this.editorConfigService.toggleShowSettings();
  }

  toggleColour() {
    this.editorConfigService.toggleShowColour();
  }

  toggleImages(isField: boolean, isObject: boolean) {
    if (isField) {
      this.isField = !(this.isField === isField);
      this.isObject = false;
    } else if (isObject) {
      this.isField = false;
      this.isObject = !(this.isObject === isObject);
    }
    this.editorConfigService.toggleImages(this.isField, this.isObject);
  }

  saveMap() {
    this.editorConfigService.saveMap();
  }

}
