import {Component, OnInit} from '@angular/core';
import {Project} from '../project';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projects: Project[] = [];

  constructor(public httpClient: HttpClient) {
    this.httpClient.get<Project[]>("/api/project")
      .subscribe((projects) => {
        this.projects = projects;
      });
  }

  ngOnInit() {
  }

}
