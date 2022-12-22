export class PenaltyPointDto {
    customerId!: number;
    penaltyPoint: number = 1;

    public constructor(id?: number) {
        if (id) {
          this.customerId = id;
          this.penaltyPoint = 1;
        }
    }
}