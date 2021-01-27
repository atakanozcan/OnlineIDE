import { Component, OnInit} from '@angular/core';
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

  editorOptions :any;
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
    this.service.getFile(this.fileName, this.projectName).subscribe(file => {
      this.file = file;
      this.editorOptions = {theme: 'vs-dark', language: this.deductProgrammingLanguage(file.name)};
      console.log(this.editorOptions)
    });
  }

  updateSourceCode(): void {
    this.service.updateSourceCode(this.fileName, this.projectName, this.file.code);
  }

  deductProgrammingLanguage(filename: string): string {
    let splits = filename.split(".");
    let fileExtension = splits[splits.length - 1]
    if(fileExtension == "java") {
      return "java"
    }
    let cExtensions = ["c", "h", "cpp", "hpp", "cc", "C", "CPP", "c++", "cxx", "ii"]
    for (let extension of cExtensions) {
      if(fileExtension == extension) {
        return "c"
      }
    }
    return "";
  }
}
