import { StaffDto } from "./staff";

export class BloodBankDto {
  bloodBankId: number= 0;
  bloodBankName: string = '';
  bloodBankStreet: string = '';
  bloodBankCity: string = '';
  bloodBankCountry: string = '';
  bloodBankDescription: string = '';
  bloodBankRating: number = 0;
  bloodBankFreeAppointments: Set<string> = new Set();
  bloodBankAdministrators: Set<StaffDto> = new Set();
  bloodBankWorkingHours: string = '';

  bloodBankStorageAPlus: number = 0;
  bloodBankStorageBPlus: number = 0;
  bloodBankStorageABPlus: number = 0;
  bloodBankStorageOPlus: number = 0;
  bloodBankStorageAMinus: number = 0;   
  bloodBankStorageBMinus: number = 0;
  bloodBankStorageABMinus: number = 0;
  bloodBankStorageOMinus: number = 0;

  public constructor(obj?: any) {
      if (obj) {
        this.bloodBankId = obj.bloodBankId;
        this.bloodBankName = obj.bloodBankName;
        this.bloodBankStreet = obj.bloodBankStreet;
        this.bloodBankCity = obj.bloodBankCity;
        this.bloodBankCountry = obj.bloodBankCountry;
        this.bloodBankDescription = obj.bloodBankDescription;
        this.bloodBankRating = obj.bloodBankRating;
        this.bloodBankFreeAppointments = obj.bloodBankFreeAppointments;
        this.bloodBankAdministrators = obj.bloodBankAdministrators;
        this.bloodBankWorkingHours = obj.bloodBankWorkingHours;

        this.bloodBankStorageAPlus = obj.bloodBankStorageAPlus;
        this.bloodBankStorageBPlus = obj.bloodBankStorageBPlus;
        this.bloodBankStorageABPlus = obj.bloodBankStorageABPlus;
        this.bloodBankStorageOPlus = obj.bloodBankStorageOPlus;
        this.bloodBankStorageAMinus = obj.bloodBankStorageAMinus;
        this.bloodBankStorageBMinus = obj.bloodBankStorageBMinus;
        this.bloodBankStorageAMinus = obj.bloodBankStorageABMinus;
        this.bloodBankStorageOMinus = obj.bloodBankStorageOMinus;
      }
  }
}
  