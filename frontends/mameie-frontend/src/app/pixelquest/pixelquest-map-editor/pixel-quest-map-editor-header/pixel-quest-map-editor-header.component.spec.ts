import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelQuestMapEditorHeaderComponent } from './pixel-quest-map-editor-header.component';

describe('PixelQuestMapEditorHeaderComponent', () => {
  let component: PixelQuestMapEditorHeaderComponent;
  let fixture: ComponentFixture<PixelQuestMapEditorHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelQuestMapEditorHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelQuestMapEditorHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
