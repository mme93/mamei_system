import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PixelLoginComponent } from './pixel-login.component';

describe('PixelLoginComponent', () => {
  let component: PixelLoginComponent;
  let fixture: ComponentFixture<PixelLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PixelLoginComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PixelLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
