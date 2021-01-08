package edu.tum.ase.project.controller;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.service.ProjectService;
import edu.tum.ase.project.service.SourceFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    SourceFileService sourceFileService;

    @PostMapping("/projects")
    public Project createProject(@RequestParam String name) {
        log.info("createProject('"+name+"')");
        return projectService.createProject(new Project(name));
    }

    @GetMapping("/projects/{name}")
    public Project getProject(@PathVariable String name) {
        return projectService.findByName(name);
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        return projectService.getProjects();
    }

    @DeleteMapping("/{projectName}")
    public void deleteProject(@PathVariable String projectName) {
        // when a project gets deleted, all of its source files should also be deleted
        // this surely can be done more elegant using the correct @OnDelete annotations in the entity models
        // I was to stupid for that though.. feel free to improve this :)
        sourceFileService.getAllFilesOfProject(projectName)
                .stream()
                .forEach(sourceFile -> sourceFileService.removeSourceFile(sourceFile));
        projectService.deleteProject(projectService.findByName(projectName));
    }

    //TODO PUT Mapping for changing project name
}
