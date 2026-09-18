package downloadmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadManager {

    private File downloadDirectory;

    private List<DownloadRecord> history;

    public DownloadManager(String directory) {

        downloadDirectory = new File(directory);

        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }

        history = new ArrayList<>();
    }

    public void startDownload(String url, String fileName) {

        // Validate URL
        if (!url.startsWith("http://")
                && !url.startsWith("https://")) {

            System.out.println("Invalid URL!");

            System.out.println(
                    "URL must start with http:// or https://"
            );

            return;
        }

        // Validate file name
        if (fileName == null
                || fileName.trim().isEmpty()) {

            System.out.println("Invalid file name!");

            return;
        }

        File outputFile =
                new File(downloadDirectory, fileName);

        DownloadRecord record =
                new DownloadRecord(
                        url,
                        fileName,
                        "DOWNLOADING"
                );

        history.add(record);

        System.out.println();
        System.out.println("Starting download...");

        System.out.println(
                "URL: " + url
        );

        System.out.println(
                "File: "
                        + outputFile.getAbsolutePath()
        );

        DownloadTask task =
                new DownloadTask(
                        url,
                        outputFile
                );

        Thread thread = new Thread(() -> {

            task.run();

            File downloadedFile =
                    new File(
                            downloadDirectory,
                            fileName
                    );

            if (downloadedFile.exists()
                    && downloadedFile.length() > 0) {

                record.setStatus("COMPLETED");

            } else {

                record.setStatus("FAILED");
            }
        });

        thread.start();
    }

    public void showHistory() {

        if (history.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No downloads yet."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "===== Download History ====="
        );

        for (DownloadRecord record : history) {

            System.out.println(record);
        }
    }
}
