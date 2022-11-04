import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationPageComponent } from "./pages/registration-page/registration-page.component";
import { LogInPageComponent } from "./pages/log-in-page/log-in-page.component";
import { StartPageComponent } from './pages/start-page/start-page.component';

const routes: Routes = [
  { path: 'registrationPage', component: RegistrationPageComponent},
  { path: 'logInPage', component: LogInPageComponent},
  { path: '', component: StartPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
