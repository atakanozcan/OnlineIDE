import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FilesService} from './files.service';
import {SourceFile} from './sourceFile';

@Component({
  selector: 'app-manage-files',
  templateUrl: './manage-files.component.html',
  styleUrls: ['./manage-files.component.css'],
  providers: [FilesService]
})
export class ManageFilesComponent implements OnInit {
  projectName: string;
  files: SourceFile[];
  newFileName?: string;

  constructor(
    private route: ActivatedRoute,
    private service: FilesService
  ) {}

  ngOnInit(): void {
    this.projectNameFromRoute();
    this.getFiles();
  }

  private projectNameFromRoute(): void {
    this.route.url.subscribe(pathArray => this.projectName = pathArray[1].toString());
  }

  getFiles(): void {
    this.service.getAllFiles(this.projectName).subscribe(files => this.files = files);
  }

  createFile(name: string, project: string): void{
    this.service.checkForFile(name, project).subscribe(exists => {
      if (exists) {
        console.log(`File ${name} already exists in project ${project}`)
      } else {
        this.service.createFile(name, project).subscribe(resp => {
          console.log(`New file ${name} was created in project ${project}`)
          this.getFiles();
        })
      }
    })
  }

  deleteFile(name: string, project: string): void{
    this.service.deleteFile(name, project).subscribe(resp => this.getFiles());
  }

  renameFile(name: string, newName: string): void{
    this.service.checkForFile(newName, this.projectName).subscribe(exists => {
      if(exists){
        console.log(`Cannot rename ${name} to ${newName} since ${newName} already exists`)
      } else{
        this.service.renameFile(name, this.projectName, newName).subscribe(resp => {
          this.getFiles();
          console.log(`File was renamed from ${name} to ${newName}!`)
        });
      }
    })

  }

}
