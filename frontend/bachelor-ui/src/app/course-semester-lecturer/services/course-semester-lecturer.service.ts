import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICourseSemesterLecturer} from '../model/ICourseSemesterLecturer';

@Injectable({
  providedIn: 'root'
})
export class CourseSemesterLecturerService {
  private readonly baseURLConfig = 'http://localhost:8080/course-semester-lecturer';

  constructor(private readonly http: HttpClient) { }

  getAllCourseSemesterLecturers(): Observable<ICourseSemesterLecturer[]>{
    return this.http.get<ICourseSemesterLecturer[]>(`${this.baseURLConfig}`);
  }

  createCourseSemesterLecturer(courseSemesterLecturer: ICourseSemesterLecturer): Observable<ICourseSemesterLecturer>{
    return this.http.post<ICourseSemesterLecturer>(`${this.baseURLConfig}`, courseSemesterLecturer);
  }

  updateCourseSemesterLecturer(id: string, courseSemesterLecturer: ICourseSemesterLecturer): Observable<ICourseSemesterLecturer>{
    return this.http.put<ICourseSemesterLecturer>(`${this.baseURLConfig}/${id}`, courseSemesterLecturer);
  }

  deleteCourseSemesterLecturer(id: string): Observable<string>{
    return this.http.delete<string>(`${this.baseURLConfig}/${id}`);
  }
}
