import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabaseProcessComponent } from './database-process.component';

describe('DatabaseProcessComponent', () => {
  let component: DatabaseProcessComponent;
  let fixture: ComponentFixture<DatabaseProcessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatabaseProcessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DatabaseProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
