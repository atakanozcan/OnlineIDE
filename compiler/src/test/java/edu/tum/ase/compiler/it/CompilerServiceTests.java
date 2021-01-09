package edu.tum.ase.compiler.it;

import edu.tum.ase.compiler.controller.CompilerService;
import edu.tum.ase.compiler.model.SourceCode;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;


@RunWith(SpringRunner.class)
public class CompilerServiceTests {

    @Autowired
    CompilerService serviceUnderTest;

    private static final String javaFileName = "App.java";
    private static final String cFileName = "App.c";


    @Test
    public void should_ReturnNoStdErr_WhenJavaCodeCompiles() throws IOException {
        //given
        SourceCode sourceCode = new SourceCode();
        String rightJavaCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\");}}";
        sourceCode.setCode(rightJavaCode);
        sourceCode.setFileName(javaFileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isEmpty();
        then(result.isCompilable()).isEqualTo(true);
    }

    @Test
    public void should_ReturnNoStdErr_WhenCCodeCompiles() throws IOException {
        //given
        SourceCode sourceCode = new SourceCode();
        String rightCCode = "#include<stdio.h>\n" + "int main(){printf (\"Hi World\\n\");return 0;}";
        sourceCode.setCode(rightCCode);
        sourceCode.setFileName(cFileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isEmpty();
        then(result.isCompilable()).isEqualTo(true);
    }

    @Test
    public void should_ReturnAnStdErr_WhenJavaCodeFailsToCompile() throws IOException {
        //given
        SourceCode sourceCode = new SourceCode();
        String wrongJavaCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\")}}";
        sourceCode.setCode(wrongJavaCode);
        sourceCode.setFileName(javaFileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isNotEmpty();
        then(result.isCompilable()).isEqualTo(false);
    }

    @Test
    public void should_ReturnAnStdErr_WhenCCodeFailsToCompile() throws IOException {
        //given
        SourceCode sourceCode = new SourceCode();
        String wrongCCode = "#include<stdio.h>\n" + "int main(){printf (\"Hi World\\n\");return 0}";
        sourceCode.setCode(wrongCCode);
        sourceCode.setFileName(cFileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isNotEmpty();
        then(result.isCompilable()).isEqualTo(false);
    }

    @Test(expected = IOException.class)
    public void should_ThrowIOException_WhenFileNameIsMissing() throws IOException {
        //given
        SourceCode sourceCode = new SourceCode();
        String rightCCode = "#include <stdio.h> int main() { printf(\"Hello, World!\"); return 0;}";
        sourceCode.setCode(rightCCode);
        sourceCode.setFileName("");

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);
    }

    @TestConfiguration
    static class CompilerServiceTestsConfiguration {

        @Bean
        public CompilerService serviceUnderTest() {
            return new CompilerService();
        }
    }
}
