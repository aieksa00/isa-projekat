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
  { path: 'complaints', component: ComplaintsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
