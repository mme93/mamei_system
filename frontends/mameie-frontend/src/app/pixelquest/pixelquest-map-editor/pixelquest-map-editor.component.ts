import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestMapEditorElementComponent } from "./pixelquest-map-editor-element/pixelquest-map-editor-element.component";
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PixelquestHeaderComponent } from '../pixel-content/pixelquest-header/pixelquest-header.component';
import { PixelQuestMapEditorHeaderComponent } from './pixel-quest-map-editor-header/pixel-quest-map-editor-header.component';
import { EditorConfigService } from 'src/app/service/editor/editor-config.service';
import { EditorDialogService } from 'src/app/service/editor/dialog/editor-dialog.service';
import { NewMap, NewMapGridElement, NewMapGridRow, NewMapImage, NewMapSettings } from 'src/app/model/config';
import { DialogService } from 'primeng/dynamicdialog';
import { MapEditorService } from 'src/app/service/data/map/map-editor.service';

@Component({
  selector: 'app-pixelquest-map-editor',
  standalone: true,
  imports: [PixelquestMapEditorElementComponent, FormsModule, CommonModule, FormsModule, PixelquestHeaderComponent, PixelQuestMapEditorHeaderComponent],
  providers: [EditorDialogService, DialogService],
  templateUrl: './pixelquest-map-editor.component.html',
  styleUrl: './pixelquest-map-editor.component.scss'
})
export class PixelquestMapEditorComponent implements OnInit {

  newMap: NewMap = this.mapEditorService.loadMap();

  screenSize: { width: number, height: number } | null = null;
  sideBarSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private mapEditorService: MapEditorService, private screenSizeService: ScreenService, private editorConfigService: EditorConfigService, private editorDialogService: EditorDialogService) {

  }

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
      this.sideBarSize = {
        width: (size.width * 0.13),
        height: (size.height)
      };
      this.newMap.settings.blockHight = (size.height * 0.7) / this.newMap.settings.rows;
      this.newMap.settings.blockWidth = (size.width * 0.8) / this.newMap.settings.cols;
    });

    this.subscription = this.editorConfigService.buttonAction$.subscribe(buttonAction => {
      if (buttonAction.buttonTyp === 'color') {
        this.editorDialogService.openPixelQuestMapEditorColor(this.newMap.images.imageType).subscribe((result: NewMapImage) => {
          if (result && result.category === 'objects') {
            this.newMap.images.objects = result.src;
          } else if (result && result.category === 'fields') {
            this.newMap.images.fields = result.src;
          }
          this.newMap.images
        });
      } else if (buttonAction.buttonTyp === 'settings') {
        this.editorDialogService.openPixelQuestMapEditorSettings(this.newMap.settings).subscribe(result => {
          if (result) {
            this.newMap.settings = result
            this.updateGrid();
          }
        });
      } else if (buttonAction.buttonTyp === 'image') {
        this.newMap.images.imageType = buttonAction.imageType;
      } else if (buttonAction.buttonTyp === 'saveMap') {
        this.mapEditorService.saveMap();
      }

    });

    this.updateGrid();
  }

  updateGrid() {
    if (this.screenSize) {
      this.newMap.settings.blockHight = (this.screenSize.height) / this.newMap.settings.rows;
      this.newMap.settings.blockWidth = (this.screenSize.width) / this.newMap.settings.cols;
      for (let i: number = 0; i < this.newMap.settings.rows; i++) {
        let elements: NewMapGridElement[] = [];
        for (let j: number = 0; j < this.newMap.settings.cols; j++) {
          elements.push({
            field_image: '/assets/stone_ground_field.png',
            field_object_image: ''
          })
        }
        this.newMap.grid.push({ elements: elements });
      }
    }
  }

  changeElement(element: NewMapGridElement) {
    if (this.newMap.images.imageType === 'field') {
      element.field_image = this.newMap.images.fields;
    } else if (this.newMap.images.imageType === 'object') {
      element.field_object_image = this.newMap.images.objects;
    }
  }

}