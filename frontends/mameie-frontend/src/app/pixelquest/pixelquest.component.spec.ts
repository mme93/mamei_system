import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelquestComponent } from './pixelquest.component';

describe('PixelquestComponent', () => {
  let component: PixelquestComponent;
  let fixture: ComponentFixture<PixelquestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelquestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelquestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
