import { Component, OnInit} from '@angular/core';
import {FilesService} from '../manage-files/files.service';
import {ActivatedRoute} from '@angular/router';
import {SourceFile} from '../manage-files/sourceFile';
import {CompilerService} from "./compiler.service";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css'],
  providers: [FilesService, CompilerService]
})
export class EditorComponent implements OnInit {

  editorOptions :any;
  projectName: string;
  fileName: string;
  compilationResult: string;
  file: SourceFile = {name: '', code: ''};
  lastSavedCode: string;
  saveWarning: string;

  constructor(private filesService: FilesService,
              private compiler: CompilerService,
              private route: ActivatedRoute) { }

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
    this.filesService.getFile(this.fileName, this.projectName).subscribe(file => {
      this.file = file;
      this.editorOptions = {theme: 'vs-dark', language: this.deductProgrammingLanguage(file.name)};
      console.log(this.editorOptions)
    });
  }

  saveFile(): void {
    this.filesService.updateSourceCode(this.fileName, this.projectName, this.file.code);
    this.lastSavedCode = this.file.code;
    this.saveWarning = "";
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

  compileFile(file: SourceFile): void {
    if(this.lastSavedCode != this.file.code){
      this.saveWarning = "You need to save the file first!"
      return
    }
    this.compiler.compile(file).subscribe(response =>{
      console.log(response)
      if(response.compilable) {
        this.compilationResult = "Successfully compiled. Output: " + response.stdout;
      } else {
        this.compilationResult = response.stderr;
      }
    })
  }
}
