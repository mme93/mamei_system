import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemSetupComponent } from './item-setup.component';

describe('ItemSetupComponent', () => {
  let component: ItemSetupComponent;
  let fixture: ComponentFixture<ItemSetupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemSetupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
