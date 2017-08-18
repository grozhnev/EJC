package task11;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Class: ParseCSV
 * Version: 0.4
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date: 2017-08-18
 * <p>
 * Description: Parse csv-files with 10 or less threads. Each thread can parse one file.
 *              Each file contain columns "id", "url", "time" and "user".
 *              The result of parsing should be sent to one csv-file with columns: "use", "url" and "time".
 *              If there are more than one user with the same name, time should be summarized.
 *              The content table in output result should be sorted by user name.
 */

public class ParseCSV {
    /*the maps will be filled with parsed content*/
    private static Map<String, String> userUrlMap = new ConcurrentHashMap<>();
    private static Map<String, Long> urlTimeMap = new ConcurrentHashMap<>();

    private Path pathToFiles = setPath();
    private Path outputPath = Paths.get(pathToFiles.toString(), "output");
    private File[] parsingFiles = getFiles(pathToFiles);

    public static void main(String[] args) throws IOException {
        ParseCSV parser = new ParseCSV();
        parser.start();
    }

    private void start(){
        parsingProcess(parsingFiles);
        makeParsingReport(pathToFiles);
        System.out.println("Parsing of "+ parsingFiles.length + " *.csv-files complete. " +
                                                                        "Please check result in folder: " + outputPath);
    }

    private Path setPath() {
        return Paths.get("src", "main", "java", "task11", "folderWithCSV");
    }

    private File[] getFiles(Path path) {
        return new File(path.toString()).listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
    }

    private void parsingProcess(File[] files) {
        /* parsing files with no more than 10 threads */
        int threadsAmount = files.length > 10 ? 10 : files.length;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);
        for (File file : files) {
            executorService.execute(new ParsingThread(file));
        }
        executorService.shutdown();

        /* ensure the executor service is over*/
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Executor thread " + executorService.getClass().getName() + " not terminating!");
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void makeParsingReport(Path directory) {
        /*create list of users and sort them */
        LinkedList<String> userNames = new LinkedList<>();
        for (String userNameAsKey : ParseCSV.userUrlMap.keySet()) {
            userNames.add(userNameAsKey);
        }
        Collections.sort(userNames);

        /*create output folder*/
        Path outputPath = Paths.get(directory.toString(), "output");
        new File(outputPath.toString()).mkdir();
        try (PrintWriter printWriter = new PrintWriter(new File(outputPath.toString() + "/userTable.csv"))) {
            printWriter.println("user, url, time ");

            /* filling output folder */
            for (String user : userNames) {
                printWriter.println(user + ", " + ParseCSV.userUrlMap.get(user) + ", " +
                                                                ParseCSV.urlTimeMap.get(ParseCSV.userUrlMap.get(user)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class: ParsingThread
     * Version: 0.1
     * <p>
     * Created by: Georgii Rozhnev, https://github.com/grozhnev
     * Date: 2017-08-18
     * <p>
     * Description: Inner class, that executes parsing for each file a thread is calling.
     */
    class ParsingThread implements Runnable {
        private File currentlyParsingFile;

        ParsingThread(File file) {
            this.currentlyParsingFile = file;
        }

        @Override
        public void run() {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentlyParsingFile))) {
                String currentLine;
                bufferedReader.readLine();

                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] splitLine = currentLine.replaceAll(" ", "").split(",");
                    long time = splitLine[2].matches("\\d*") ? Long.parseLong(splitLine[2]) : 0;
                    ParseCSV.urlTimeMap.merge(splitLine[1], time, (a, b) -> a + b);
                    ParseCSV.userUrlMap.put(splitLine[3], splitLine[1]);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
