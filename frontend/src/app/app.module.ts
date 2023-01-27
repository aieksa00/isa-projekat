import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OverlayModule} from '@angular/cdk/overlay';
import { DatePipe } from '@angular/common'
import { CookieService } from 'ngx-cookie-service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { LogInPageComponent } from './pages/log-in-page/log-in-page.component';
import { StartPageComponent } from './pages/start-page/start-page.component';
import { UserHomePageComponent } from './pages/user-home-page/user-home-page.component';
import { SidenavComponent } from './pages/sidenav/sidenav.component';
import { BloodBanksListComponent } from './pages/blood-banks-list/blood-banks-list.component';
import { UserHistoryComponent } from './pages/user-history/user-history.component';
import { UserAppointmentsComponent } from './pages/user-appointments/user-appointments.component';
import { UserQRCodesComponent } from './pages/user-qrcodes/user-qrcodes.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { ComplaintsComponent } from './pages/complaints/complaints.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BloodBankInfoComponent } from './pages/blood-bank-info/blood-bank-info.component';
import { UserService } from './services/user.service';
import { CreateBloodBankComponent } from './pages/create-blood-bank/create-blood-bank.component';
import { BloodBankService } from './services/blood-bank.service';
import { RegistrationUserPageComponent } from './pages/registration-user-page/registration-user-page.component';
import { QuestionnairePageComponent } from './pages/questionnaire-page/questionnaire-page.component';
import { UsersListComponent } from './pages/users-list/users-list.component';
import { BloodBankCalenderComponent } from './pages/blood-bank-calender/blood-bank-calender.component';
import { DayPilotModule } from "@daypilot/daypilot-lite-angular";
import { CreateAdministratorComponent } from './pages/create-administrator/create-administrator.component';
import { JwtInterceptorService } from './helpers/jwt.interceptor.service';
import { AppointmentReviewPageComponent } from './pages/appointment-review-page/appointment-review-page.component';
import { MatExpansionModule } from '@angular/material/expansion'
import { BloodBankSpecComponent } from './pages/blood-bank-spec/blood-bank-spec.component';
import { ScheduleAppointmentComponent } from './pages/user-schedule-new-appointment-time/schedule-appointment/schedule-appointment.component';
import { CreateMedicalStaffComponent } from './pages/create-medical-staff/create-medical-staff.component';
import { StaffHomePageComponent } from './pages/staff-home-page/staff-home-page.component';
import { BloodBankCustomersComponent } from './pages/blood-bank-customers/blood-bank-customers.component';
import { StaffNavbarComponent } from './pages/staff-navbar/staff-navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationPageComponent,
    LogInPageComponent,
    StartPageComponent,
    UserHomePageComponent,
    SidenavComponent,
    BloodBanksListComponent,
    UserHistoryComponent,
    UserAppointmentsComponent,
    UserQRCodesComponent,
    UserProfileComponent,
    ComplaintsComponent,
    BloodBankInfoComponent,
    CreateBloodBankComponent,
    RegistrationUserPageComponent,
    QuestionnairePageComponent,
    UsersListComponent,
    BloodBankCalenderComponent,
    CreateAdministratorComponent,
    AppointmentReviewPageComponent,
    BloodBankSpecComponent,
    ScheduleAppointmentComponent,
    CreateMedicalStaffComponent,
    StaffHomePageComponent,
    BloodBankCustomersComponent,
    StaffNavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    OverlayModule,
    HttpClientModule,
    DayPilotModule,
    MatExpansionModule,
  ],
  providers: [CookieService, UserService, BloodBankService, DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
