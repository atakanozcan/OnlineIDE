import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class FilesService {
  constructor(private http: HttpClient) { }

  projectUrl = 'http://localhost:8080';

  getFiles(project: string): Observable<File[]>{
    const url = `${this.projectUrl}/${project}`;
    return this.http.get<File[]>(url, httpOptions).pipe();
}
}
