import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [CookieService]
})
export class AppComponent implements OnChanges{
  constructor(public cookieService: CookieService) {}
  
  title = 'frontend';
  @Input() cookie : string = this.cookieService.get('LoggedIn');

  public isLoggedIn : boolean = this.cookieService.get('LoggedIn') === 'true';

  ngOnChanges( changes : SimpleChanges) {
    const { currentValue }  = changes['cookie'];
    this.isLoggedIn = currentValue === 'true';
    console.log("usao")
  }

}
