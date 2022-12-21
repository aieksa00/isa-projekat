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

const routes: Routes = [
  { path: 'registrationPage', component: RegistrationPageComponent},
  { path: 'logInPage', component: LogInPageComponent},
  { path: '', component: StartPageComponent},
  { path: 'userHomePage', component: UserHomePageComponent},
  { path: 'sideNav', component: SidenavComponent},
  { path: 'bloodBanks', component: BloodBanksListComponent},
  { path: 'userHistory', component: UserHistoryComponent},
  { path: 'userAppointments', component: UserAppointmentsComponent},
  { path: 'userQRCodes', component: UserQRCodesComponent},
  { path: 'userProfile', component: UserProfileComponent},
  { path: 'complaints', component: ComplaintsComponent},
  { path: 'bloodBankInfo', component: BloodBankInfoComponent},
  { path: 'createBloodBank', component: CreateBloodBankComponent},
  { path: 'registrationUserPage/:id', component: RegistrationUserPageComponent},
  { path: 'questionnairePage', component: QuestionnairePageComponent},
  { path: 'userslist', component: UsersListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
