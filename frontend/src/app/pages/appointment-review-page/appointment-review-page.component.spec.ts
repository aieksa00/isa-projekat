import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentReviewPageComponent } from './appointment-review-page.component';

describe('AppointmentReviewPageComponent', () => {
  let component: AppointmentReviewPageComponent;
  let fixture: ComponentFixture<AppointmentReviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentReviewPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentReviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
