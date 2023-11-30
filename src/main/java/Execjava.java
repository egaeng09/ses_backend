import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;

public class Execjava {
    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        try {
            String runJava = "java -cp /usr/src/app Main";

            Process runProcess =
                    new ProcessBuilder("ash", "-c", runJava).
                            redirectErrorStream(true).start();

            if((args.length > 0)) {
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

        } catch (IOException | InterruptedException e) {

        }

        System.out.println(output.toString());
    }
}

