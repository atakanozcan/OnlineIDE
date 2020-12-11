package edu.tum.ase.project.repository;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.model.SourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceFileRepository extends JpaRepository<SourceFile, String> {
    SourceFile findByProjectAndName(Project project, String name);
}
