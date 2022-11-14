import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { OverlayModule} from '@angular/cdk/overlay';
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
import { HttpClientModule } from '@angular/common/http';
import { BloodBankInfoComponent } from './pages/blood-bank-info/blood-bank-info.component';

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
    BloodBankInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    OverlayModule,
    HttpClientModule,
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
