import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserQRCodesComponent } from './user-qrcodes.component';

describe('UserQRCodesComponent', () => {
  let component: UserQRCodesComponent;
  let fixture: ComponentFixture<UserQRCodesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserQRCodesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserQRCodesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
