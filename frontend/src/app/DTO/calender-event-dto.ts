import { DayPilot } from "@daypilot/daypilot-lite-angular";

export class CalenderEventDTO {
    public id: string = '';
    public start: DayPilot.Date = DayPilot.Date.today();
    public end: DayPilot.Date = DayPilot.Date.today();
    public text : String = "";
}