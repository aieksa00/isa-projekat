export class UpdateBloodBankStorageDto {
    bloodBankId!: number;
    bloodType!: string;

    public constructor(id?: number, bloodType?: string) {
        if (id || bloodType) {
          this.bloodBankId = id!;
          this.bloodType = bloodType!;
        }
    }
}