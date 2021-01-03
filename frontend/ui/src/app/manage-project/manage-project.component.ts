import { Component, OnInit } from '@angular/core';
import {ProjectService} from "./project.service";
import {Project} from "./project";

@Component({
  selector: 'app-manage-project',
  templateUrl: './manage-project.component.html',
  styleUrls: ['./manage-project.component.css'],
  providers: [ProjectService]
})

export class ManageProjectComponent implements OnInit {
  projects: Project[];

  constructor(private service: ProjectService) { }

  ngOnInit() {
    this.getProjects();
  }

  getProjects(): void {
    this.service.getProjects()
      .subscribe(projects => (this.projects = projects));
  }

  addNewProject(): void{
    console.log("New Project!");
  }

  editProject(): void{
    console.log("Goes to the editor");
  }


  deleteProject(): void{
    console.log("Deletes the project");
  }
}
