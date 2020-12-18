package edu.tum.ase.project.service;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.model.SourceFileKey;
import edu.tum.ase.project.repository.SourceFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SourceFileService {
    @Autowired
    private SourceFileRepository sourceFileRepository;
    private static final Logger log = LoggerFactory.getLogger(SourceFileRepository.class);
    
    public SourceFile createSourceFile(SourceFile sourceFile){
        try {
            log.info("SourceFile created. Project: " + sourceFile.getProject() + " Name: " + sourceFile.getName());
            return sourceFileRepository.save(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public SourceFile findByProjectAndName(Project project, String name){
        return sourceFileRepository.findBySourceFileKey(new SourceFileKey(project, name));
    }
    
    public List<SourceFile> getAllSourceFiles(){
        return sourceFileRepository.findAll();
    }

    public List<SourceFile> getAllFilesOfProject(String projectName) {
        return getAllSourceFiles()
                .stream()
                .filter(sourceFile -> sourceFile.getProject().getName().equals(projectName))
                .collect(Collectors.toList());
    }
    
    public void removeSourceFile(SourceFile sourceFile) {
        sourceFileRepository.delete(sourceFile);
    }
    
    public void updateSourceFile(SourceFile sourceFile) {
        sourceFileRepository.save(sourceFile);
    }
}