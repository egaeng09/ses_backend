//package com.web.back.utils;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//import java.util.concurrent.*;
//
//public class CompileManager {
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    String inputFilePath;
//    String outputFilePath;
//
//    public String compileJava(String inputCode, String inputTestcase) {
//        StringBuilder output = new StringBuilder();
//
//        try {
//            // 클래스 이름을 임의로 지정 (원하는 로직에 따라 변경 가능)
//            String fileName = "Main";
//
//            // 코드를 파일로 저장
//            String filePath = "/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + fileName + ".java";
////            String filePath = "C:\\Users\\KWON\\Desktop\\" + fileName + ".java";
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//                writer.write(inputCode);
//            }
//
//            // 저장한 파일을 컴파일
//            String compileCommand = "javac " + filePath;
////            String compileCommand = "javac -source 1.8 -target 1.8 " + filePath;
//            Process compileProcess = Runtime.getRuntime().exec(compileCommand);
//            compileProcess.waitFor();
//            compileProcess.destroy();
//
//            File classFile = new File("/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + fileName + ".class");
//            if (!classFile.exists()) {
//                output.append("컴파일 에러");
//                javaFileDelete();
//                return output.toString();
//            }
//
//            //컴파일된 파일 실행
//            output = dockerJavaRun(inputTestcase);
//
//            javaFileDelete();
//
//        } catch (IOException | InterruptedException e) {
//            output.append(e.toString()).append("\n");
//        }
//
//        // 결과를 문자열로 반환
//        return output.toString();
//    }
//    public void javaFileDelete(){
//        File f1 = new File("/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + "Main" + ".java");
//        f1.delete();
//        File f2 = new File("/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + "Main"+ ".class");
//        f2.delete();
//    }
//    public StringBuilder dockerJavaRun(String inputTestcase) throws IOException, InterruptedException {
//        StringBuilder output = new StringBuilder();
//
//        //2번 CPU 사용, 1GB 메모리 할당
////        String runJava = "docker run --platform linux/amd64 --rm --cpuset-cpus 1 --memory=1g -v /Users/jeonghwan-il/Desktop/backend-docker-27/src/main/java:/usr/src/app taek2/env:1.0 java Execjava \"" + inputTestcase + "\"";
//        String runJava = "docker run --cpuset-cpus 1 --memory=1g -v /Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java:/usr/src/app taek2/env:1.0 java Execjava \"" + inputTestcase + "\"";
//        Process runProcess = new ProcessBuilder("bash", "-c", runJava).redirectErrorStream(true).start();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
//            String resultLine;
//            while ((resultLine = br.readLine()) != null) {
//                output.append(resultLine).append("\n");
//            }
//        }
//        runProcess.waitFor();
//
//        return output;
//    }
//    public String compileCpp(String inputCode,String inputTestcase) {
//        StringBuilder output = new StringBuilder();
//
//        try {
//            String fileName = "Main";
//
//            BufferedReader reader = new BufferedReader(new StringReader(inputCode));
//            StringBuilder code = new StringBuilder();
//
//            String convertedCpp = convertCpp(inputCode);
//
//            // 입력한 코드를 파일로 저장
//            String filePath = "/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + fileName + ".cpp";
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//                writer.write(convertedCpp);
//            }
//
////            // 저장한 파일을 컴파일
////            String compileCommand = "g++ -o /Users/jeonghwan-il/Desktop/backend-docker-27/src/main/java/" + fileName + " /Users/jeonghwan-il/Desktop/backend-docker-27/src/main/java/" + fileName + ".cpp";
////            Process compileProcess = Runtime.getRuntime().exec(compileCommand);
////            compileProcess.waitFor(10000, TimeUnit.MILLISECONDS);
////            compileProcess.destroy();
////
////            File classFile = new File("/Users/jeonghwan-il/Desktop/backend-docker-27/src/main/java/" + fileName);
////            if (!classFile.exists()) {
////                output.append("컴파일 에러\n");
////                cppFileDelete();
////                return output.toString();
////            }
////            System.out.println(inputTestcase);
//            // C++ 프로그램 실행 명령
//            output = dockerCppRun(inputTestcase);
//
//            cppFileDelete();
//
//        } catch (IOException | InterruptedException e) {
//            output.append(e.toString()).append("\n");
//        }
//
//        // 결과를 문자열로 반환
//        return output.toString();
//    }
//    public String convertCpp(String code){
//        return code.replaceAll("using", "\nusing");
//    }
//    public void cppFileDelete(){
//        File f1 = new File("/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + "Main" + ".cpp");
//        f1.delete();
//        File f2 = new File("/Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java/" + "Main");
//        f2.delete();
//    }
//    public String compileTimeout(Callable<String> codeExecution, long timeoutMillis) {
//        try {
//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            Future<String> future = executor.submit(codeExecution);
//            return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
//        } catch (TimeoutException e) {
//            javaFileDelete();
//            cppFileDelete();
//            return "시간 초과";
//        } catch (Exception e) {
//            return e.toString();
//        }
//    }
//    public StringBuilder dockerCppRun(String inputTestcase) throws IOException, InterruptedException {
//        StringBuilder output = new StringBuilder();
//
////        String runJava = "docker run --platform linux/amd64 --rm --cpuset-cpus 1 --memory=1g -v /Users/jeonghwan-il/Desktop/backend-docker-27/src/main/java:/usr/src/app taek2/env:1.0 sh -c \"g++ -o Main Main.cpp && java Execcpp '" + inputTestcase + "'\"";
//        String runJava = "docker run --rm --cpuset-cpus 1 --memory=1g -v /Users/jeonghwan-il/Desktop/backend-docker-1129/src/main/java:/usr/src/app taek2/env:1.0 sh -c \"g++ -o Main Main.cpp && java Execcpp '" + inputTestcase + "'\"";
//        Process runProcess = new ProcessBuilder("bash", "-c", runJava).redirectErrorStream(true).start();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
//            String resultLine;
//            while ((resultLine = br.readLine()) != null) {
//                output.append(resultLine).append("\n");
//            }
//        }
//        runProcess.waitFor();
//
//        return output;
//    }
//}
//위로는 맥 코드
//**********************************************************************
//밑으로는 윈도우 코드
package com.web.back.utils;


        import java.io.*;
        import java.nio.charset.StandardCharsets;
        import java.util.Scanner;
        import java.util.concurrent.*;

public class CompileManager {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String inputFilePath;
    String outputFilePath;

    public String compileJava(String inputCode, String inputTestcase) {
        StringBuilder output = new StringBuilder();

        try {
            // 클래스 이름을 임의로 지정 (원하는 로직에 따라 변경 가능)
            String fileName = "Main";

            // 코드를 파일로 저장
            String filePath = "C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + fileName + ".java";
//            String filePath = "C:\\Users\\KWON\\Desktop\\" + fileName + ".java";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(inputCode);
            }

            // 저장한 파일을 컴파일
//            String compileCommand = "javac " + filePath;
            String compileCommand = "javac -source 1.8 -target 1.8 " + filePath;
            Process compileProcess = Runtime.getRuntime().exec(compileCommand);
            compileProcess.waitFor();
            compileProcess.destroy();

            File classFile = new File("C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + fileName + ".class");
            if (!classFile.exists()) {
                output.append("컴파일 에러");
                javaFileDelete();
                return output.toString();
            }

            //컴파일된 파일 실행
            output = dockerJavaRun(inputTestcase);

            javaFileDelete();

        } catch (IOException | InterruptedException e) {
            output.append(e.toString()).append("\n");
        }

        // 결과를 문자열로 반환
        return output.toString();
    }
    public void javaFileDelete(){
        File f1 = new File("C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + "Main" + ".java");
        f1.delete();
        File f2 = new File("C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + "Main"+ ".class");
        f2.delete();
    }
    public StringBuilder dockerJavaRun(String inputTestcase) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();

        //2번 CPU 사용, 1GB 메모리 할당
        String runJava = "docker run --rm --cpuset-cpus 1 --memory=1g -v C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\:/usr/src/app taek2/env:1.0 java Execjava \"" + inputTestcase + "\"";

        // Process runProcess = new ProcessBuilder("bash", "-c", runJava).redirectErrorStream(true).start();
        Process runProcess = new ProcessBuilder("cmd.exe", "/c", runJava).redirectErrorStream(true).start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
            String resultLine;
            while ((resultLine = br.readLine()) != null) {
                output.append(resultLine).append("\n");
            }
        }
        runProcess.waitFor();

        return output;
    }
    public String compileCpp(String inputCode,String inputTestcase) {
        StringBuilder output = new StringBuilder();

        try {
            String fileName = "Main";

            BufferedReader reader = new BufferedReader(new StringReader(inputCode));
            StringBuilder code = new StringBuilder();

            String convertedCpp = convertCpp(inputCode);

            // 입력한 코드를 파일로 저장
            String filePath = "C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + fileName + ".cpp";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(convertedCpp);
            }

            // C++ 프로그램 실행 명령
            output = dockerCppRun(inputTestcase);

            cppFileDelete();

        } catch (IOException | InterruptedException e) {
            output.append(e.toString()).append("\n");
        }

        // 결과를 문자열로 반환
        return output.toString();
    }
    public String convertCpp(String code){
        return code.replaceAll("using", "\nusing");
    }
    public void cppFileDelete(){
        File f1 = new File("C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + "Main" + ".cpp");
        f1.delete();
        File f2 = new File("C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\" + "Main");
        f2.delete();
    }
    public String compileTimeout(Callable<String> codeExecution, long timeoutMillis) {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(codeExecution);
            return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            javaFileDelete();
            cppFileDelete();
            return "시간 초과";
        } catch (Exception e) {
            return e.toString();
        }
    }
    public StringBuilder dockerCppRun(String inputTestcase) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();

        String runJava = "docker run --rm --cpuset-cpus 1 --memory=1g -v C:\\Users\\Home\\Desktop\\backend-docker\\src\\main\\java\\:/usr/src/app taek2/env:1.0 java Execcpp \"" + inputTestcase + "\"";

        // Process runProcess = new ProcessBuilder("bash", "-c", runJava).redirectErrorStream(true).start();
        Process runProcess = new ProcessBuilder("cmd.exe", "/c", runJava).redirectErrorStream(true).start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
            String resultLine;
            while ((resultLine = br.readLine()) != null) {
                output.append(resultLine).append("\n");
            }
        }
        runProcess.waitFor();

        return output;
    }
}
