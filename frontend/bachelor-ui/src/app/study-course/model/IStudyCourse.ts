import {ITimePeriod} from '../../time-period/model/ITimePeriod';

export interface IStudyCourse {
  id?: string;
  studyCourseName: string;
  numberOfSemesters: number;
  timePeriod: ITimePeriod;  // Полный объект для работы с ним
}
