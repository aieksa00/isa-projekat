export class UpdateBloodBankDto {
    bloodBankName: string = '';
    bloodBankStreet: string = '';
    bloodBankCity: string = '';
    bloodBankCountry: string = '';
    bloodBankDescription: string = '';

    public constructor(obj?: any) {
        if (obj) {
          this.bloodBankName = obj.bloodBankName;
          this.bloodBankStreet = obj.bloodBankStreet;
          this.bloodBankCity = obj.bloodBankCity;
          this.bloodBankCountry = obj.bloodBankCountry;
          this.bloodBankDescription = obj.bloodBankDescription;    
        }
    }
}