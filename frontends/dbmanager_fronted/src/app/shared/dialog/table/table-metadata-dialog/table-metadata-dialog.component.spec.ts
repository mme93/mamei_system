import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableMetadataDialogComponent } from './table-metadata-dialog.component';

describe('TableMetadataDialogComponent', () => {
  let component: TableMetadataDialogComponent;
  let fixture: ComponentFixture<TableMetadataDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableMetadataDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableMetadataDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
