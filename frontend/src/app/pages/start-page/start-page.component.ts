import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { LogInPageComponent } from '../log-in-page/log-in-page.component';

@Component({
  selector: 'app-start-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.css']
})
export class StartPageComponent implements OnInit {

  constructor(private dialogRef : MatDialog) {
  }

  openLogInDialog() {
    this.dialogRef.open(LogInPageComponent, {
      width: "500px",
    });
  }

  closeLogInDialog() {
    this.dialogRef.closeAll();
  }

  ngOnInit(): void {
  }

}
