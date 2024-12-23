import { ComponentFixture, TestBed } from '@angular/core/testing';

import { XElementComponent } from './x-element.component';

describe('XElementComponent', () => {
  let component: XElementComponent;
  let fixture: ComponentFixture<XElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [XElementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(XElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
