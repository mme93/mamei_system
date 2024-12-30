import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanvasContentComponent } from './canvas-content.component';

describe('CanvasContentComponent', () => {
  let component: CanvasContentComponent;
  let fixture: ComponentFixture<CanvasContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CanvasContentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CanvasContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
