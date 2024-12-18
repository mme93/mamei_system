import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelContentComponent } from './pixel-content.component';

describe('PixelContentComponent', () => {
  let component: PixelContentComponent;
  let fixture: ComponentFixture<PixelContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelContentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
