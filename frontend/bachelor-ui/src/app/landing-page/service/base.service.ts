import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {catchError, map, Observable, of} from 'rxjs';
import {ILecturer} from '../../lecturer/model/ILecturer';


@Injectable({
  providedIn: 'root'
})
export class BaseService {

  private readonly baseURLConfig = 'http://localhost:8080';
  constructor(private readonly http: HttpClient) {
  }

  getLecturers(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get<any>(`${this.baseURLConfig}/lecturer`, {headers}).pipe(
      map((response: HttpResponse<ILecturer[]>) => response || []),
      catchError(() => {
        console.log('aaaaaa')
        return of([])
      })
    )
  }
}
