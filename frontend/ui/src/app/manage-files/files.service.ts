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

  createFile(name: string, project: string): void{
    // TODO: check if there is already a file with the same name.
    //  here or maybe in manage-files component. -> should there be an error message?
    const url = `${this.projectUrl}/${project}/${name}`;
    this.http.post<any>(url, '', httpOptions).subscribe(response => console.log(response));
    console.log(`File created, name: ${name}, project: ${project}`);
  }
}
