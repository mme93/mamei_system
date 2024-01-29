import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScopeDialogComponent } from './scope-dialog.component';

describe('ScopeDialogComponent', () => {
  let component: ScopeDialogComponent;
  let fixture: ComponentFixture<ScopeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScopeDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScopeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
