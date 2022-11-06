import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBanksListComponent } from './blood-banks-list.component';

describe('BloodBanksListComponent', () => {
  let component: BloodBanksListComponent;
  let fixture: ComponentFixture<BloodBanksListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBanksListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBanksListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
