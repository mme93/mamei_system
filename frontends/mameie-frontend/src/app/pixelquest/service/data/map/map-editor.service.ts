import { Injectable } from '@angular/core';
import { NewMap } from 'src/app/pixelquest/model/config';

@Injectable({
  providedIn: 'root'
})
export class MapEditorService {

  constructor() { }

  saveMap() {

  }

  loadMap(): NewMap {
    return {
      images: {
        imageType: 'empty',
        fields: '/assets/stone_ground_field.png',
        objects: '/assets/objects/bonfire.png'
      },
      settings: {
        title: 'New Title',
        rows: 14,
        cols: 32,
        blockWidth: 0,
        blockHight: 0
      },
      grid: []
    };
  }

}
