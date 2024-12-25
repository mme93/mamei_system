import { Injectable } from '@angular/core';
import { DynamicDialogRef, DialogService } from 'primeng/dynamicdialog';
import { Observable } from 'rxjs';
import { NewMapSettings } from 'src/app/model/config';
import { PixelquestMapEditorSettingsComponent } from 'src/app/pixelquest/pixelquest-map-editor/dialogs/pixelquest-map-editor-settings/pixelquest-map-editor-settings.component';

@Injectable({
  providedIn: 'root'
})
export class EditorDialogService {
  
    ref: DynamicDialogRef | undefined;
    constructor(public dialogService: DialogService) { }
  
    openPixelQuestMapEditorSettings(settings: NewMapSettings):Observable<NewMapSettings>{
      return this.dialogService.open(PixelquestMapEditorSettingsComponent, {
            header: 'Map Editor Settings',
            height: '50vh',
            width: '80vh',
            data: settings
          }).onClose;
    }
  }
