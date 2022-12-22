import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankSpecComponent } from './blood-bank-spec.component';

describe('BloodBankSpecComponent', () => {
  let component: BloodBankSpecComponent;
  let fixture: ComponentFixture<BloodBankSpecComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankSpecComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankSpecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
