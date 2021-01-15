import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SourceFile} from './sourceFile';
import {map} from "rxjs/operators";

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

  getAllFiles(project: string): Observable<SourceFile[]>{
    const url = `${this.projectUrl}/${project}`;
    return this.http.get<SourceFile[]>(url, httpOptions).pipe();
  }

  getFile(name: string, project: string): Observable<SourceFile> {
    const url = `${this.projectUrl}/${project}/${name}`;
    return this.http.get<SourceFile>(url, httpOptions).pipe();
  }

  checkForFile(name: string, project: string): Observable<boolean>{
    const url = `${this.projectUrl}/${project}/${name}`;
    return this.http.get(url, {observe: 'response'}).pipe(map(resp => resp.body != null))
  }

  createFile(name: string, project: string): Observable<any> {
    const url = `${this.projectUrl}/${project}/${name}`;
    return this.http.post(url, '', httpOptions)
  }

  updateSourceCode(fileName: string, project: string, sourceCode: string): void {
    const url = `${this.projectUrl}/${project}/${fileName}`;
    console.log(`${project}/${fileName} source code updated to: ${sourceCode}`);
    this.http.put<any>(url, sourceCode, httpOptions);
  }

  deleteFile(name: string, project: string): Observable<any>{
    const url = `${this.projectUrl}/${project}/${name}`;
    console.log(`${project}/${name} was deleted`);
    return this.http.delete(url, httpOptions);
  }
}
