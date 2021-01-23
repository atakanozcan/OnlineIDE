package edu.tum.ase.compiler.unit;

import edu.tum.ase.compiler.controller.CompilerService;
import edu.tum.ase.compiler.model.SourceCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;


@RunWith(SpringRunner.class)
public class CompilerServiceTests {
    @Autowired
    CompilerService serviceUnderTest;

    @MockBean
    BufferedReader reader;

    private  static final String fileName = "App.java";

    private static final String[] stderr = {"App.java:1: error: ';' expected",
            "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\")}}",
            "                                                                                           ^",
            "1 error\n"};
    @Test
    public void should_ReturnNoStdErr_WhenCodeCompiles() throws IOException {
        //given
        given(reader.readLine()).willReturn(null);

        SourceCode sourceCode = new SourceCode();
        String rightCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\");}}";
        sourceCode.setCode(rightCode);
        sourceCode.setFileName(fileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isEqualTo("");
        then(result.isCompilable()).isEqualTo(true);
    }

    @Test
    public void should_ReturnAnStdErr_WhenCodeFailsToCompile() throws IOException {
        //given
        given(reader.lines()).willReturn(Arrays.stream(stderr.clone()));

        SourceCode sourceCode = new SourceCode();
        String wrongCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\")}}";
        sourceCode.setCode(wrongCode);
        sourceCode.setFileName(fileName);

        //when
        SourceCode result = serviceUnderTest.compile(sourceCode);

        //then
        then(result.getStderr()).isEqualTo(String.join("\n", stderr));
        then(result.isCompilable()).isEqualTo(false);
    }

    @TestConfiguration
    static class CompilerServiceTestsConfiguration {
        @Bean
        public CompilerService serviceUnderTest() {
            return new CompilerService();
        }
    }

}
