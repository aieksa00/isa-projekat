import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankCalenderComponent } from './blood-bank-calender.component';

describe('BloodBankCalenderComponent', () => {
  let component: BloodBankCalenderComponent;
  let fixture: ComponentFixture<BloodBankCalenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankCalenderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankCalenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
