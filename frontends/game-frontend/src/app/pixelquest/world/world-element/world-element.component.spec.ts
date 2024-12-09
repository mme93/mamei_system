import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorldElementComponent } from './world-element.component';

describe('WorldElementComponent', () => {
  let component: WorldElementComponent;
  let fixture: ComponentFixture<WorldElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WorldElementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorldElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
