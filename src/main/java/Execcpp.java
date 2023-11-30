import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.*;
        import java.util.concurrent.TimeUnit;

public class Execcpp {
    public static void main(String[] args) {
        try {
            String compileCommand = "g++ -o Main Main.cpp";
            Process compileProcess = new ProcessBuilder("ash", "-c", compileCommand).
                    redirectErrorStream(true).start();
            compileProcess.waitFor(10000, TimeUnit.MILLISECONDS);
            compileProcess.destroy();

            File classFile = new File("/usr/src/app/Main");
            if (!classFile.exists()) {
                System.out.println("컴파일 에러");
            }

            StringBuilder output = new StringBuilder();

            String runCpp = "/usr/src/app/Main";

            Process runProcess =
                    new ProcessBuilder("ash", "-c", runCpp).
                            redirectErrorStream(true).start();

            if ( args.length > 0 ) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()))) {
                    writer.write(args[0]);
                }
            }

            try (
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
                String resultLine;
                while ((resultLine = br.readLine()) != null) {
                    output.append(resultLine).append("\n");
                }
            }

            runProcess.waitFor();
            System.out.println(output.toString());
        } catch (IOException | InterruptedException e) {
            System.out.println("CATCH ERROR in Execcpp");
        }
    }
}