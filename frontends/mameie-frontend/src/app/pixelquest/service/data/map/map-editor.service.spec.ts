import { TestBed } from '@angular/core/testing';

import { MapEditorService } from './map-editor.service';

describe('MapEditorService', () => {
  let service: MapEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MapEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
