import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestMapEditorSettingsComponent } from './pixelquest-map-editor-settings.component';

describe('PixelquestMapEditorSettingsComponent', () => {
  let component: PixelquestMapEditorSettingsComponent;
  let fixture: ComponentFixture<PixelquestMapEditorSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestMapEditorSettingsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestMapEditorSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
