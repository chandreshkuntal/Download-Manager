package downloadmanager;

public class DownloadRecord {

    private String url;
    private String fileName;
    private String status;

    public DownloadRecord(String url, String fileName, String status) {
        this.url = url;
        this.fileName = fileName;
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return fileName + " | " + status + " | " + url;
    }
}
