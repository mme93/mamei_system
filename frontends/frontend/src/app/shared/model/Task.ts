import { Time } from "@angular/common";

export interface StandardTask {
  name: string;
  information: string;
  startDate: Date;
  endDate: Date;
  startTime:Time;
  endTime:Time;
}
