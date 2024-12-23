import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestMapEditorContentComponent } from './pixelquest-map-editor-content.component';

describe('PixelquestMapEditorContentComponent', () => {
  let component: PixelquestMapEditorContentComponent;
  let fixture: ComponentFixture<PixelquestMapEditorContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestMapEditorContentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestMapEditorContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
