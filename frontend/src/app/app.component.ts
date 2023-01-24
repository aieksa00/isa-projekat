import { Component, Inject, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [CookieService]
})
export class AppComponent implements OnChanges{
  constructor(public cookieService: CookieService, public authenticationService : AuthenticationService) {}
  
  title = 'frontend';
  @Input() cookie : string = this.cookieService.get('LoggedIn');

  ngOnChanges( changes : SimpleChanges) {
    const { currentValue }  = changes['cookie'];
  }

}
