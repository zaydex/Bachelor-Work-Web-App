import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ICourse} from '../model/icourse';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private readonly baseURLConfig: string = 'http://localhost:8080/course';

  constructor(private readonly http: HttpClient) { }

  getAllCourses() {
    return this.http.get<ICourse[]>(`${this.baseURLConfig}`);
  }

  createCourse(course: ICourse){
    return this.http.post<ICourse>(`${this.baseURLConfig}`, course);
  }

  updateCourse(id: string, course: ICourse){
    return this.http.put(`${this.baseURLConfig}/${id}`, course);
  }

  deleteCourse(id: string){
    return this.http.delete(`${this.baseURLConfig}/${id}`);
  }
}
