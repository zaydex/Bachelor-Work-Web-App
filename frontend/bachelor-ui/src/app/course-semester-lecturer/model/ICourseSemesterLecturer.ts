import {ICourseSemester} from '../../course-semester/model/ICourseSemester';
import {ILecturer} from '../../lecturer/model/ILecturer';

export interface ICourseSemesterLecturer {
  id?: string;
  courseSemester: ICourseSemester;
  lecturer: ILecturer;
  comment: string;
  numberOfStudents: number;
  numberOfGroups: number;
}
