import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SourceFile} from './sourceFile';

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

  getFiles(project: string): Observable<SourceFile[]>{
    const url = `${this.projectUrl}/${project}`;
    return this.http.get<SourceFile[]>(url, httpOptions).pipe();
  }

  getFile(name: string, project: string): Observable<SourceFile> {
    const url = `${this.projectUrl}/${project}/${name}`;
    return this.http.get<SourceFile>(url, httpOptions).pipe();
  }

  createFile(name: string, project: string): void{
    // TODO: check if there is already a file with the same name.
    //  here or maybe in manage-files component. -> should there be an error message?
    const url = `${this.projectUrl}/${project}/${name}`;
    this.http.post<any>(url, '', httpOptions).subscribe(response => console.log(response));
    console.log(`File created, name: ${name}, project: ${project}`);
  }

  updateSourceCode(fileName: string, project: string, sourceCode: string): void {
    const url = `${this.projectUrl}/${project}/${fileName}`;
    this.http.put<any>(url, sourceCode, httpOptions)
      .subscribe(response => console.log(`response: ${response}`));
    console.log(`${project}/${fileName} source code updated to: ${sourceCode}`);
  }
}
