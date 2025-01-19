import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketSettingsDialogComponent } from './ticket-settings-dialog.component';

describe('TicketSettingsDialogComponent', () => {
  let component: TicketSettingsDialogComponent;
  let fixture: ComponentFixture<TicketSettingsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketSettingsDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketSettingsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
