import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ILecturer} from '../model/ILecturer';

@Injectable({
  providedIn: 'root'
})
export class LecturerService {
  private readonly baseURLConfig = 'http://localhost:8080/lecturer';

  constructor(private readonly http: HttpClient) {}

  getAllLecturers(): Observable<ILecturer[]> {
    return this.http.get<ILecturer[]>(`${this.baseURLConfig}`)
  }

  createLecturer(lecturer: ILecturer){
    return this.http.post<ILecturer>(`${this.baseURLConfig}`, lecturer)
  }

  updateLecturer(id:string, lecturer: ILecturer){
    return this.http.put<ILecturer>(`${this.baseURLConfig}/${id}`, lecturer)
  }

  deleteLecturer(id: string): Observable<any>{
    return this.http.delete<any>(`${this.baseURLConfig}/${id}`)
  }
}
