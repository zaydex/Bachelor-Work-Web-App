import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICourseSemester} from '../model/ICourseSemester';

@Injectable({
  providedIn: 'root'
})
export class CourseSemesterService {
  private readonly baseURLConfig = 'http://localhost:8080/course-semester';

  constructor(private readonly http: HttpClient) { }

  getAllCourseSemesters(): Observable<ICourseSemester[]>{
    return this.http.get<any>(`${this.baseURLConfig}`);
  }

  createCourseSemester(courseSemester: ICourseSemester){
    return this.http.post<any>(`${this.baseURLConfig}`, courseSemester);
  }

  updateCourseSemester(id: string, courseSemester: ICourseSemester){
    return this.http.put<any>(`${this.baseURLConfig}/${id}`, courseSemester);
  }

  deleteCourseSemester(id: string){
    return this.http.delete<any>(`${this.baseURLConfig}/${id}`);
  }
}
