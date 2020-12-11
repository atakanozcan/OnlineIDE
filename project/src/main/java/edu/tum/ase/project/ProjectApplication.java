package edu.tum.ase.project;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.service.ProjectService;
import edu.tum.ase.project.service.SourceFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	ProjectService projectService;
	
	@Autowired
	SourceFileService sourceFileService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("DataSource = " + dataSource);
		
		String projectName = "my-project";
		
		while(projectService.findByName(projectName) != null) {
			log.info("The project name " + projectName + " is already taken.");
			projectName = projectName + Math.round(Math.random()*10);
		}
		
		Project project = projectService.createProject(new Project(projectName)); 
		log.info("ID of saved project = " + project.getId());
		
		Project p = projectService.findByName(projectName);
		log.info("ID of queried project = " + p.getId());
		
		List<Project> projects = projectService.getProjects();
		log.info("Length of project list = " + projects.size());

		SourceFile sourceFile = sourceFileService.createSourceFile(new SourceFile(project, "testfile"));
		log.info("ID of saved sourcefile: " + sourceFile.getId());
		
		SourceFile s = sourceFileService.findByProjectAndName(project, "testfile");
	}
}
