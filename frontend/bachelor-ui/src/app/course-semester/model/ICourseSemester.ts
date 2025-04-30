import {ISemester} from '../../study-course/model/ISemester';
import {ICourse} from '../../course/model/icourse';

export interface ICourseSemester {
  id?: string;
  course: ICourse;
  semester: ISemester;
  toPlan: boolean;
}
