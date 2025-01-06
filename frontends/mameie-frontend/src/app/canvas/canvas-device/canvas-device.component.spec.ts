import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanvasDeviceComponent } from './canvas-device.component';

describe('CanvasDeviceComponent', () => {
  let component: CanvasDeviceComponent;
  let fixture: ComponentFixture<CanvasDeviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CanvasDeviceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CanvasDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
