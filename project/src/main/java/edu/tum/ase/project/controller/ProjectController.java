package edu.tum.ase.project.controller;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.service.ProjectService;
import edu.tum.ase.project.service.SourceFileService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    ProjectService projectService;

    @Autowired
    SourceFileService sourceFileService;


    @Autowired
    private OAuth2RestOperations operations;

    @PostMapping("/projects")
    public Project createProject(@RequestParam String name) {
        log.info("createProject('"+name+"')");
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return projectService.createProject(new Project(name, authentication.getName()));
    }

    @GetMapping("/projects/{name}")
    public Project getProject(@PathVariable String name) {
        return projectService.findByName(name);
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects(){
        return projectService.getProjects().stream().filter(p -> p.getUserIds().contains(SecurityContextHolder.getContext().getAuthentication().getName())).collect(Collectors.toList());
    }

    @DeleteMapping("/{projectName}")
    public void deleteProject(@PathVariable String projectName) {
        // when a project gets deleted, all of its source files should also be deleted
        // this surely can be done more elegant using the correct @OnDelete annotations in the entity models
        // I was to stupid for that though.. feel free to improve this :)
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (projectService.findByName(projectName).getUserIds().contains(authentication.getName())) {
            sourceFileService.getAllFilesOfProject(projectName)
                    .stream()
                    .forEach(sourceFile -> sourceFileService.removeSourceFile(sourceFile));
            projectService.deleteProject(projectService.findByName(projectName));
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/projects/{oldName}")
    public Project renameProject(@PathVariable String oldName, @RequestBody String newName) {
        if (!oldName.equals(newName)) {
            Project newProject = new Project(newName, SecurityContextHolder.getContext().getAuthentication().getName());
            projectService.createProject(newProject);
            sourceFileService.getAllFilesOfProject(oldName)
                    .stream()
                    .forEach(sourceFile ->
                            sourceFileService.createSourceFile(new SourceFile(newProject,
                                    sourceFile.getName(), sourceFile.getCode()))
                    );
            deleteProject(oldName);
            return newProject;
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/projects/{projectName}/{userId}")
    public void shareProject(@PathVariable String projectName, @PathVariable String userId) {
        String jsonStr = operations.getForObject(
                "https://gitlab.lrz.de/api/v4/search?scope=users&search="+ userId,
                String.class
        );

        assert jsonStr != null;
        JSONObject data = new JSONObject(jsonStr.substring(1, jsonStr.length() - 1));
        if(data.has("username") && data.get("username").equals(userId))
        {
                projectService.addUserId(projectName, userId);
        }
        else
        {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
