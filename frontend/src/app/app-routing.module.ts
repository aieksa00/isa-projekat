import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistrationPageComponent } from "./pages/registration-page/registration-page.component";
import { LogInPageComponent } from "./pages/log-in-page/log-in-page.component";
import { StartPageComponent } from './pages/start-page/start-page.component';
import { UserHomePageComponent } from './pages/user-home-page/user-home-page.component';
import { SidenavComponent } from './pages/sidenav/sidenav.component';
import { BloodBanksListComponent } from './pages/blood-banks-list/blood-banks-list.component';
import { UserHistoryComponent } from './pages/user-history/user-history.component';
import { UserAppointmentsComponent } from './pages/user-appointments/user-appointments.component';
import { UserQRCodesComponent } from './pages/user-qrcodes/user-qrcodes.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { ComplaintsComponent } from './pages/complaints/complaints.component';
import { BloodBankInfoComponent } from './pages/blood-bank-info/blood-bank-info.component';
import { CreateBloodBankComponent } from './pages/create-blood-bank/create-blood-bank.component';
import { RegistrationUserPageComponent } from './pages/registration-user-page/registration-user-page.component';
import { QuestionnairePageComponent } from './pages/questionnaire-page/questionnaire-page.component';
import { UsersListComponent } from './pages/users-list/users-list.component';
import { BloodBankCalenderComponent } from './pages/blood-bank-calender/blood-bank-calender.component';
import { CreateAdministratorComponent } from './pages/create-administrator/create-administrator.component';
import { AppointmentReviewPageComponent } from './pages/appointment-review-page/appointment-review-page.component';
import { BloodBankSpecComponent } from './pages/blood-bank-spec/blood-bank-spec.component';
import { ScheduleAppointmentComponent } from './pages/user-schedule-new-appointment-time/schedule-appointment/schedule-appointment.component';
import { RoleGuard } from './helpers/auth-guard.guard';
import { CreateMedicalStaffComponent } from './pages/create-medical-staff/create-medical-staff.component';
import { StaffHomePageComponent } from './pages/staff-home-page/staff-home-page.component';

const routes: Routes = [
  { path: 'registrationPage', component: RegistrationPageComponent},
  { path: 'logInPage', component: LogInPageComponent},
  { path: '', component: StartPageComponent},
  { path: 'userHomePage', component: UserHomePageComponent},
  { path: 'sideNav', component: SidenavComponent},
  { path: 'bloodBanks', component: BloodBanksListComponent},
  { path: 'userHistory', component: UserHistoryComponent},
  { path: 'userAppointments', component: UserAppointmentsComponent, canActivate: [RoleGuard],
          data: { 
            expectedRole: 'CUSTOMER'
          }
  },
  { path: 'userQRCodes', component: UserQRCodesComponent},
  { path: 'userProfile', component: UserProfileComponent},
  { path: 'complaints', component: ComplaintsComponent},
  { path: 'bloodBankInfo', component: BloodBankInfoComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'STAFF'
          }
  },
  { path: 'createBloodBank', component: CreateBloodBankComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'ADMIN'
          }
  },
  { path: 'registrationUserPage/:id', component: RegistrationUserPageComponent},
  { path: 'questionnairePage', component: QuestionnairePageComponent, canActivate: [RoleGuard],
          data: { 
            expectedRole: 'CUSTOMER'
          }
  },
  { path: 'userslist', component: UsersListComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'ADMIN'+'STAFF'
          }
  },
  { path: 'calender', component: BloodBankCalenderComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'STAFF'
          }
  },
  { path: 'addAdmin', component: CreateAdministratorComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'ADMIN'
          }
  },
  { path: 'addMedicalStaff', component: CreateMedicalStaffComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'ADMIN'
          }
  },
  { path: 'appointmentReview/:id', component: AppointmentReviewPageComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'STAFF'
          }
  },
  { path: 'bloodBankSpec', component: BloodBankSpecComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'CUSTOMER'+'STAFF'
          }
  },
  { path: 'scheduleAppointment', component: ScheduleAppointmentComponent, canActivate: [RoleGuard]},
  { path: 'staffHomePage', component: StaffHomePageComponent, canActivate: [RoleGuard], 
          data: { 
            expectedRole: 'STAFF'
          }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
