import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FilesService} from './files.service';

@Component({
  selector: 'app-manage-files',
  templateUrl: './manage-files.component.html',
  styleUrls: ['./manage-files.component.css'],
  providers: [FilesService]
})
export class ManageFilesComponent implements OnInit {
  projectName: string;
  files: File[];
  newFileName?: string;

  constructor(
    private route: ActivatedRoute,
    private service: FilesService
  ) {}

  ngOnInit(): void {
    this.route.url.subscribe(l => this.projectName = l[1].toString());
    this.getFiles();
  }

  getFiles(): void {
    this.service.getFiles(this.projectName).subscribe(files => this.files = files);
  }

  createFile(name: string, project: string): void{
    this.service.createFile(name, project);
  }

}
