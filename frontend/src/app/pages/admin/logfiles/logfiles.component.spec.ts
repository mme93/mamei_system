import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogfilesComponent } from './logfiles.component';

describe('LogfilesComponent', () => {
  let component: LogfilesComponent;
  let fixture: ComponentFixture<LogfilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogfilesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogfilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
