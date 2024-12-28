import { Component } from '@angular/core';
import { DialogService, DynamicDialogModule, DynamicDialogRef } from 'primeng/dynamicdialog';
import { EditorConfigService } from 'src/app/service/editor/editor-config.service';
import { PixelquestMapEditorSettingsComponent } from '../dialogs/pixelquest-map-editor-settings/pixelquest-map-editor-settings.component';
import { NewMapSettings } from 'src/app/model/config';
import { CommonModule } from '@angular/common';

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
    console.log('SAVE MAP')
  }

}
