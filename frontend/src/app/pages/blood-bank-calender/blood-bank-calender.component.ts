//SOURCE: https://code.daypilot.org/30451/angular-calendar-day-week-month-views

import {Component, ViewChild, AfterViewInit} from "@angular/core";
import { Router } from "@angular/router";
import {
        DayPilot,
        DayPilotCalendarComponent,
        DayPilotMonthComponent,
        DayPilotNavigatorComponent }
        from "@daypilot/daypilot-lite-angular";
import { CalenderEventDTO } from "src/app/DTO/calender-event-dto";
import { BloodBankService } from "src/app/services/blood-bank.service";

@Component({
  selector: 'app-blood-bank-calender',
  templateUrl: './blood-bank-calender.component.html',
  styleUrls: ['./blood-bank-calender.component.css']
})
export class BloodBankCalenderComponent implements AfterViewInit {

  @ViewChild("day") day!: DayPilotCalendarComponent;
  @ViewChild("week") week!: DayPilotCalendarComponent;
  @ViewChild("month") month!: DayPilotMonthComponent;
  @ViewChild("navigator") nav!: DayPilotNavigatorComponent;

  events: DayPilot.EventData[] = [];

  date = DayPilot.Date.today();

  configNavigator: DayPilot.NavigatorConfig = {
    showMonths: 3,
    cellWidth: 25,
    cellHeight: 25,
    onVisibleRangeChanged: args => {
      this.loadEvents();
    }
  };

  selectTomorrow() {
    this.date = DayPilot.Date.today().addDays(1);
  }

  changeDate(date: DayPilot.Date): void {
    this.configDay.startDate = date;
    this.configWeek.startDate = date;
    this.configMonth.startDate = date;
  }

  configDay: DayPilot.CalendarConfig = {
    viewType: "Day",
    onEventMove : async(args) =>{
      const dp = args.control;
      dp.eventMoveHandling = "Disabled"
    },
    onEventResize: (args) =>{
      const dp = args.control;
      dp.eventResizeHandling = "Disabled"
    },
    onEventClicked: async (args) => {
      if(args.e.end() < DayPilot.Date.now()){
        const modal = await DayPilot.Modal.confirm("Review appointment?")
        const dp = args.control;
        dp.clearSelection();
        if (!(modal).result) { return; }
        this.router.navigate(['/appointmentReview/'+args.e.id()])
      }
    }
  };

  configWeek: DayPilot.CalendarConfig = {
    viewType: "Week",
    onEventMoved : async(args) =>{
      const dp = args.control;
      dp.eventMoveHandling = "Disabled"
    },
    onEventMove : async(args) =>{
      const dp = args.control;
      dp.eventMoveHandling = "Disabled"
    },
    onEventResize: (args) =>{
      const dp = args.control;
      dp.eventResizeHandling = "Disabled"
    },
    onEventClicked: async (args) => {
      if(args.e.end() < DayPilot.Date.now()){
        const modal = await DayPilot.Modal.confirm("Review appointment?")
        const dp = args.control;
        dp.clearSelection();
        if (!(modal).result) { return; }
        this.router.navigate(['/appointmentReview/'+args.e.id()])
      }
    }
    
  };

  configMonth: DayPilot.MonthConfig = {
    onEventMove : async(args) =>{
      const dp = args.control;
      dp.eventMoveHandling = "Disabled"
    },
    onEventClicked: async (args) => {
      if(args.e.end() < DayPilot.Date.now()){
        const modal = await DayPilot.Modal.confirm("Review appointment?")
        const dp = args.control;
        dp.clearSelection();
        if (!(modal).result) { return; }
        this.router.navigate(['/appointmentReview/'+args.e.id()])
      }
    },
    onEventResize: (args) =>{
      const dp = args.control;
      dp.eventResizeHandling = "Disabled"
    },
  };

  constructor(private bloodBankService: BloodBankService, private router: Router) {
    this.viewWeek();
  }

  ngAfterViewInit(): void {
    const from = this.nav.control.visibleStart();
    const to = this.nav.control.visibleEnd();
    this.loadEvents();
  }

  loadEvents(): void {
    
    this.bloodBankService.getCalenderEventsForBloodBank(localStorage.getItem("email")).subscribe(result => {
      this.events = result;
    });
  }

  viewDay():void {
    this.configNavigator.selectMode = "Day";
    this.configDay.visible = true;
    this.configWeek.visible = false;
    this.configMonth.visible = false;
  }

  viewWeek():void {
    this.configNavigator.selectMode = "Week";
    this.configDay.visible = false;
    this.configWeek.visible = true;
    this.configMonth.visible = false;
  }

  viewMonth():void {
    this.configNavigator.selectMode = "Month";
    this.configDay.visible = false;
    this.configWeek.visible = false;
    this.configMonth.visible = true;
    this.configMonth.width = '1140';
  }


}

