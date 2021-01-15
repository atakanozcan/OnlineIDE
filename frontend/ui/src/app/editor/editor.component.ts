import { Component, OnInit } from '@angular/core';
import {FilesService} from '../manage-files/files.service';
import {ActivatedRoute} from '@angular/router';
import {SourceFile} from '../manage-files/sourceFile';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css'],
  providers: [FilesService]
})
export class EditorComponent implements OnInit {
  editorOptions = {theme: 'vs-dark', language: 'javascript'};
  projectName: string;
  fileName: string;
  file: SourceFile = {name: '', code: ''};

  constructor(private service: FilesService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.fillProjectAndFileName();
    this.loadFile();
  }

  private fillProjectAndFileName(): void {
    this.route.url.subscribe(pathArray => {
      this.projectName = pathArray[1].toString();
      this.fileName = pathArray[2].toString();
    });
  }

  loadFile(): void {
    this.service.getFile(this.fileName, this.projectName).subscribe(file => this.file = file);
  }

  updateSourceCode(): void {
    this.service.updateSourceCode(this.fileName, this.projectName, this.file.code);
  }
}
