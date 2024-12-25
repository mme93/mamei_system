import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { NewMapSettings } from 'src/app/model/config';

@Component({
  selector: 'app-pixelquest-map-editor-settings',
  standalone: true,
  imports: [FormsModule, InputTextModule, ButtonModule],
  templateUrl: './pixelquest-map-editor-settings.component.html',
  styleUrl: './pixelquest-map-editor-settings.component.scss'
})
export class PixelquestMapEditorSettingsComponent {

  mapSettings: NewMapSettings = this.config.data;

  constructor(private ref: DynamicDialogRef, private config: DynamicDialogConfig) { }

  takeOver() {
    this.ref.close(this.mapSettings);
  }

  close() {
    this.ref.close(null);
  }

}
