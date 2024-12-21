import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestHeaderComponent } from './pixelquest-header.component';

describe('PixelquestHeaderComponent', () => {
  let component: PixelquestHeaderComponent;
  let fixture: ComponentFixture<PixelquestHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
