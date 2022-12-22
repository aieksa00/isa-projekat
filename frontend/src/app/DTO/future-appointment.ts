import { StaffDto } from "./staff";

export class FutureAppointmentDto {
    appointmentId: number = 0;
    duration: number = 0;
    time: number = 0;
    scheduleDateTime: string = '';
    medicalStaff: Array<StaffDto> = [];

    public constructor(obj?: any) {
        if (obj) {
          this.appointmentId = obj.appointmentId;
          this.duration = obj.duration;
          this.scheduleDateTime = obj.scheduleDateTime;
          this.medicalStaff = obj.medicalStaff;
        }
    }
}
