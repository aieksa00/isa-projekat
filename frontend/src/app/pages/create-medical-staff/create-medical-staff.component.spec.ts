import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMedicalStaffComponent } from './create-medical-staff.component';

describe('CreateMedicalStaffComponent', () => {
  let component: CreateMedicalStaffComponent;
  let fixture: ComponentFixture<CreateMedicalStaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMedicalStaffComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateMedicalStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
