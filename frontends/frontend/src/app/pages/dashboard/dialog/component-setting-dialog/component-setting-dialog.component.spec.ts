import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentSettingDialogComponent } from './component-setting-dialog.component';

describe('ComponentSettingDialogComponent', () => {
  let component: ComponentSettingDialogComponent;
  let fixture: ComponentFixture<ComponentSettingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComponentSettingDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComponentSettingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
