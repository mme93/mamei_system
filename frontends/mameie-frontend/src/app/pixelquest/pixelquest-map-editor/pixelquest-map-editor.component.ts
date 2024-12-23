import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestMapEditorElementComponent } from "./pixelquest-map-editor-element/pixelquest-map-editor-element.component";
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PixelquestHeaderComponent } from '../pixel-content/pixelquest-header/pixelquest-header.component';
import { PixelQuestMapEditorHeaderComponent } from './pixel-quest-map-editor-header/pixel-quest-map-editor-header.component';
import { EditorConfigService } from 'src/app/service/editor/editor-config.service';

@Component({
  selector: 'app-pixelquest-map-editor',
  standalone: true,
  imports: [PixelquestMapEditorElementComponent, FormsModule, CommonModule, PixelquestHeaderComponent, PixelQuestMapEditorHeaderComponent],
  templateUrl: './pixelquest-map-editor.component.html',
  styleUrl: './pixelquest-map-editor.component.scss'
})
export class PixelquestMapEditorComponent implements OnInit {
  showSettings = true;
  dummy: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;
  rows: number = 14;
  cols: number = 32;
  screenSize: { width: number, height: number } | null = null;
  sideBarSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenService, private editorConfigService: EditorConfigService) {

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
      this.blockHight = (size.height * 0.7) / this.rows;
      this.blockWidth = (size.width * 0.8) / this.cols;
    });
    for (let i: number = 0; i < this.rows; i++) {
      let x: String[] = [];
      for (let j: number = 0; j < this.cols; j++) {
        x.push('Test');
      }
      this.dummy.push(x)

    }
    this.subscription = this.editorConfigService.showSettings$.subscribe(editorConfig => {
      this.showSettings = editorConfig;
    })
  }

}
