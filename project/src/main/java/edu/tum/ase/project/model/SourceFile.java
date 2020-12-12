package edu.tum.ase.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "project_source_file")
public class SourceFile {
    protected SourceFile(){}
    
    public SourceFile(Project project, String name) {
        sourceFileKey = new SourceFileKey(project, name);
    }

    // A SourceFile is uniquely identified by its name (path) and the project it belongs to.
    @EmbeddedId
    private SourceFileKey sourceFileKey;
    
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String code;

    public Project getProject() {
        return sourceFileKey.getProject();
    }

    public void setProject(Project project) {
        sourceFileKey.setProject(project);
    }

    public String getName() {
        return sourceFileKey.getName();
    }

    public void setName(String name) {
        sourceFileKey.setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SourceFileKey getSourceFileKey() {
        return sourceFileKey;
    }

    public void setSourceFileKey(SourceFileKey key) {
        this.sourceFileKey = key;
    }
}
