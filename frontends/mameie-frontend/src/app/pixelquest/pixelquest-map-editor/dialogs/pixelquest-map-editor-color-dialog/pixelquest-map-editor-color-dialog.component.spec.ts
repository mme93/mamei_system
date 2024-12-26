import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestMapEditorColorDialogComponent } from './pixelquest-map-editor-color-dialog.component';

describe('PixelquestMapEditorColorDialogComponent', () => {
  let component: PixelquestMapEditorColorDialogComponent;
  let fixture: ComponentFixture<PixelquestMapEditorColorDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestMapEditorColorDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestMapEditorColorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
