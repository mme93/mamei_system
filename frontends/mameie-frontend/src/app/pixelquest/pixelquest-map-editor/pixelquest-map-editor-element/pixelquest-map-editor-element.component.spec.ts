import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestMapEditorElementComponent } from './pixelquest-map-editor-element.component';

describe('PixelquestMapEditorElementComponent', () => {
  let component: PixelquestMapEditorElementComponent;
  let fixture: ComponentFixture<PixelquestMapEditorElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestMapEditorElementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestMapEditorElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
