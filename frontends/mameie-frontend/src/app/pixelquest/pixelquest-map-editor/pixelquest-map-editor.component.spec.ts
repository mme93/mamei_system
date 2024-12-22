import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestMapEditorComponent } from './pixelquest-map-editor.component';

describe('PixelquestMapEditorComponent', () => {
  let component: PixelquestMapEditorComponent;
  let fixture: ComponentFixture<PixelquestMapEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestMapEditorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestMapEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
