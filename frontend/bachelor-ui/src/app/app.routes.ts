import { Routes } from '@angular/router';
import {LecturerComponent} from './lecturer/lecturer.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {LecturerTypeComponent} from './lecturer-type/lecturer-type.component';
import {StudyCourseComponent} from './study-course/study-course.component';
import {TimePeriodComponent} from './time-period/time-period.component';
import {CourseComponent} from './course/course.component';
import {CourseSemesterComponent} from './course-semester/course-semester.component';
import {CourseSemesterLecturerComponent} from './course-semester-lecturer/course-semester-lecturer.component';

export const routes: Routes = [
  {
    path: '', component: LandingPageComponent
  },
  {
    path: 'time-period', component: TimePeriodComponent
  },
  {
    path: 'lecturer', component: LecturerComponent
  },
  {
    path: 'lecturer-type', component: LecturerTypeComponent
  },
  {
    path: 'study-course', component: StudyCourseComponent
  },
  {
    path: 'course', component: CourseComponent
  },
  {
    path: 'course-semester', component: CourseSemesterComponent
  },
  {
    path: 'course-semester-lecturer', component: CourseSemesterLecturerComponent
  }
];
