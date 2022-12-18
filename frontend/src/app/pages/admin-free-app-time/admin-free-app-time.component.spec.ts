import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFreeAppTimeComponent } from './admin-free-app-time.component';

describe('AdminFreeAppTimeComponent', () => {
  let component: AdminFreeAppTimeComponent;
  let fixture: ComponentFixture<AdminFreeAppTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFreeAppTimeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminFreeAppTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
