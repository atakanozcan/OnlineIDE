import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Project} from "./project";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http:HttpClient) { }

  projectUrl = 'http://localhost:8080';

  num: number = 1;

  /** GET projects from the server */
  getProjects(): Observable<Project[]> {
    const url = `${this.projectUrl}/projects`;
    return this.http.get<Project[]>(url, httpOptions).pipe();
  }

  /** DELETE: delete the project from the server */
  deleteProject(name: string): Observable<{}> {
    const url = `${this.projectUrl}/${name}`;
    return this.http.delete(url, httpOptions)
      .pipe();
  }


  /** POST: add a new project to the database */
  addProject(projectName: string): Observable<Project> {
    const url = `${this.projectUrl}/projects`;
    const options = projectName ?
      { params: new HttpParams().set('name', projectName) } : {};
    return this.http.post<Project>(url, '', options).pipe();
  }

}
