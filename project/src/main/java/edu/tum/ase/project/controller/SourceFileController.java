package edu.tum.ase.project.controller;

import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.service.ProjectService;
import edu.tum.ase.project.service.SourceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SourceFileController {
    @Autowired
    SourceFileService sourceFileService;
    
    @Autowired
    ProjectService projectService;
    
    @GetMapping("/{projectName}/{fileName}")
    public SourceFile getSourceFile(@PathVariable String projectName, @PathVariable String fileName) {
        return sourceFileService.findByProjectAndName(projectService.findByName(projectName), fileName);
    }
    
    @GetMapping("/{projectName}")
    public List<SourceFile> getAllFilesOfProject(@PathVariable String projectName) {
        return sourceFileService.getAllFilesOfProject(projectName);
    }
    
    @PostMapping("/{projectName}/{fileName}")
    public SourceFile createFile(@PathVariable String projectName, @PathVariable String fileName) {
        return sourceFileService.createSourceFile(new SourceFile(projectService.findByName(projectName),
                fileName));
    }
    
    @DeleteMapping("/{projectName}/{fileName}")
    public void deleteFile(@PathVariable String projectName, @PathVariable String fileName) {
        sourceFileService.removeSourceFile(getSourceFile(projectName, fileName));
    }
    
    @PutMapping("/{projectName}/{fileName}")
    public SourceFile updateSourceCode(@PathVariable String projectName, @PathVariable String fileName,
                                       @RequestParam String sourceCode) {
        SourceFile sourceFile = sourceFileService
                .findByProjectAndName(projectService.findByName(projectName), fileName);
        sourceFile.setCode(sourceCode);
        sourceFileService.updateSourceFile(sourceFile);
        return sourceFile;
    }

    //TODO PUT Mapping for renaming file
}
