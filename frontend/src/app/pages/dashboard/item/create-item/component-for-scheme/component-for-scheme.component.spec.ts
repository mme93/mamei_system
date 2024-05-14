import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentForSchemeComponent } from './component-for-scheme.component';

describe('ComponentForSchemeComponent', () => {
  let component: ComponentForSchemeComponent;
  let fixture: ComponentFixture<ComponentForSchemeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComponentForSchemeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComponentForSchemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
