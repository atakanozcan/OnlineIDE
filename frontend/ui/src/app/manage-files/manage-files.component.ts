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
    this.service.getFiles(this.projectName).subscribe(files => this.files = files);
  }

  createFile(name: string, project: string): void{
    this.service.createFile(name, project);
  }

}
