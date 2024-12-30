import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelElementComponent } from './pixel-element.component';

describe('PixelElementComponent', () => {
  let component: PixelElementComponent;
  let fixture: ComponentFixture<PixelElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelElementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
