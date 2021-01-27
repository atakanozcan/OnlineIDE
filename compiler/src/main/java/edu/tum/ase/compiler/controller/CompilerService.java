package edu.tum.ase.compiler.controller;

import edu.tum.ase.compiler.model.SourceCode;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.Runtime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CompilerService {

    public CompilerService() {
    }

    public SourceCode compile(SourceCode sourceCode) throws IOException {
        Process process = null;
        BufferedReader reader = null;
        InputStream stream = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        String command = "";

        //Create the executable file from sourcecode object
        File executableFile = new File(sourceCode.getName());
        executableFile.createNewFile();

        //Delete file after JVM terminates
        executableFile.deleteOnExit();
        Path path = Paths.get(sourceCode.getName());
        Files.write(path, sourceCode.getCode().getBytes());

        //Specify the compiler
        try {
            String extension = sourceCode.getName().split("\\.")[1];
            if (extension.equals("java")) {
                command = "javac";
            } else if (extension.equals("c")) {
                command = "gcc";
            } else {
                sourceCode.setStderr("Invalid File Extension!");
                return sourceCode;
            }
        } catch (Exception e) {
            sourceCode.setStderr("Invalid File Extension!");
            return sourceCode;
        }



        //Compile the file
        try {
            process = Runtime.getRuntime().exec(command+" "+sourceCode.getName());
            reader = new BufferedReader( new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine())!= null)
            {
                buffer.append(line + "\n");
            }
            sourceCode.setStderr(buffer.toString());
            if (sourceCode.getStderr().equals("")){
                sourceCode.setCompilable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCode;
    }
}
