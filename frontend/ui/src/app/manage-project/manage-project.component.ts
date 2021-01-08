import { Component, OnInit } from '@angular/core';
import {ProjectService} from './project.service';
import {Project} from './project';

@Component({
  selector: 'app-manage-project',
  templateUrl: './manage-project.component.html',
  styleUrls: ['./manage-project.component.css'],
  providers: [ProjectService]
})

export class ManageProjectComponent implements OnInit {
  projects: Project[];
  value?: string;

  constructor(private service: ProjectService) { }

  ngOnInit(): void {
    this.getProjects();
  }

  getProjects(): void {
    this.service.getProjects()
      .subscribe(projects => (this.projects = projects));
  }

  addNewProject(): void{
    this.service.addProject(this.value)
      .subscribe(project => this.projects.push(project));
    this.getProjects();
  }

  editProject(): void{
    console.log('Goes to the editor');
  }

  deleteProject(project: Project): void {
    this.projects = this.projects.filter(h => h !== project);
    this.service
      .deleteProject(project.name)
      .subscribe();
  }
}
