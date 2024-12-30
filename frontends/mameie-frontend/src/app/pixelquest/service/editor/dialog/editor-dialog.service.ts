import { Injectable } from '@angular/core';
import { DynamicDialogRef, DialogService } from 'primeng/dynamicdialog';
import { Observable } from 'rxjs';
import { NewMapSettings } from 'src/app/pixelquest/model/config';
import { PixelquestMapEditorColorDialogComponent } from 'src/app/pixelquest/pixelquest-map-editor/dialogs/pixelquest-map-editor-color-dialog/pixelquest-map-editor-color-dialog.component';
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

    openPixelQuestMapEditorColor(category:string){
      return this.dialogService.open(PixelquestMapEditorColorDialogComponent, {
            header: 'Map Editor Settings',
            height: '50vh',
            width: '80vh',
            data: category
          }).onClose;
    }
  }
