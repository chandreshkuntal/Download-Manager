# Java Download Manager

## Project Description

Java Download Manager is a command-line application developed using Java.

The application allows users to download files from a URL and save them
inside a local downloads directory.

## Features

- Download files from URLs
- Display download progress
- Save downloaded files locally
- Run downloads using a separate thread
- Maintain download history
- Command-line interface
- Basic error handling

## Technologies Used

- Java
- Java Networking
- Java File I/O
- Java Threads
- VS Code

## Requirements

- JDK 17 or later
- VS Code
- Internet connection

## Project Structure

java-download-manager/

    src/
        downloadmanager/
            Main.java
            DownloadManager.java
            DownloadTask.java
            DownloadRecord.java

    downloads/

    README.md

## How to Run in VS Code

1. Open the project folder in VS Code.
2. Install the Extension Pack for Java.
3. Open `src/downloadmanager/Main.java`.
4. Click the Run button.
5. Use the command-line menu.

## How to Run from Terminal

Open the project folder in a terminal.

Compile the project:

    javac -d out src\downloadmanager\*.java

Run the application:

    java -cp out downloadmanager.Main

## How to Use

After starting the program, the following menu is displayed:

1. Download File
2. Download History
3. Exit

Select option 1 to download a file.

Enter the URL of the file.

Enter the name that should be used to save the file.

The downloaded file is stored inside the `downloads` directory.

Select option 2 to view the download history.

Select option 3 to exit the application.

## Output

Downloaded files are stored in:

    downloads/

## Author

Your Name
