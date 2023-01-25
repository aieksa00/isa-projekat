import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankCustomersComponent } from './blood-bank-customers.component';

describe('BloodBankCustomersComponent', () => {
  let component: BloodBankCustomersComponent;
  let fixture: ComponentFixture<BloodBankCustomersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankCustomersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
