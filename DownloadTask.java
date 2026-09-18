package downloadmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

public class DownloadTask implements Runnable {

    private String url;
    private File outputFile;

    public DownloadTask(String url, File outputFile) {
        this.url = url;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {

        HttpURLConnection connection = null;

        try {
            URI uri = URI.create(url);

            connection = (HttpURLConnection) uri.toURL().openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {

                System.out.println(
                        "\nDownload failed. HTTP Code: "
                                + responseCode
                );

                return;
            }

            long fileSize = connection.getContentLengthLong();

            try (
                    InputStream input = connection.getInputStream();
                    FileOutputStream output =
                            new FileOutputStream(outputFile)
            ) {

                byte[] buffer = new byte[8192];

                long downloaded = 0;
                int bytesRead;

                while ((bytesRead = input.read(buffer)) != -1) {

                    output.write(buffer, 0, bytesRead);

                    downloaded += bytesRead;

                    showProgress(downloaded, fileSize);
                }
            }

            System.out.println("\nDownload completed!");

            System.out.println(
                    "Saved to: "
                            + outputFile.getAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "\nDownload failed: "
                            + e.getMessage()
            );

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void showProgress(long downloaded, long total) {

        if (total <= 0) {

            System.out.print(
                    "\rDownloaded: "
                            + downloaded
                            + " bytes"
            );

            return;
        }

        int percentage =
                (int) ((downloaded * 100) / total);

        System.out.print(
                "\rProgress: "
                        + percentage
                        + "%"
        );
    }
}
