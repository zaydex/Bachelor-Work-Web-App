import {IStudyCourse} from './IStudyCourse';

export interface ISemester {
  id?: string;
  semesterNumber: number;
  numberOfStudents?: number;
  studyCourse?: IStudyCourse;
}
