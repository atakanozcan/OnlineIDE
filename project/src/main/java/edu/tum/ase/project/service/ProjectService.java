package edu.tum.ase.project.service;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    
    public Project createProject(Project project) {
        if(findByName(project.getName()) != null) {
            log.warn("Project name " + project.getName() + " was already taken");
            return null;
        }
        try {
            log.info("Create Project. ID=" + project.getId() + " name=" + project.getName());
            return projectRepository.save(project);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
    
    public List<Project> getProjects() {
        List<Project> list = projectRepository.findAll();
        return list;
    }
}
