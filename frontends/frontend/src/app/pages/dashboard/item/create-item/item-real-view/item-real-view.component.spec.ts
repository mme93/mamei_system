import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemRealViewComponent } from './item-real-view.component';

describe('ItemRealViewComponent', () => {
  let component: ItemRealViewComponent;
  let fixture: ComponentFixture<ItemRealViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemRealViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemRealViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
