
package com.quartercode.qcutil.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import com.quartercode.qcutil.Progressable;

public class File extends java.io.File {

    private static final long  serialVersionUID = 1498332710152236519L;

    public static final String lineSeperator    = System.getProperty("line.seperator");

    public static java.io.File convert(File file) {

        return new java.io.File(file.getPath());
    }

    public static File convert(java.io.File file) {

        return new File(file.getPath());
    }

    public static java.io.File[] convert(File[] oldFiles) {

        java.io.File[] newFiles = new java.io.File[oldFiles.length];

        for (int counter = 0; counter < oldFiles.length; counter++) {
            newFiles[counter] = convert(oldFiles[counter]);
        }

        return newFiles;
    }

    public static File[] convert(java.io.File[] oldFiles) {

        File[] newFiles = new File[oldFiles.length];

        for (int counter = 0; counter < oldFiles.length; counter++) {
            newFiles[counter] = convert(oldFiles[counter]);
        }

        return newFiles;
    }

    public File(String path) {

        super(path);
    }

    public File(URI uri) {

        super(uri);
    }

    public File(String pathPart1, String pathPart2) {

        super(pathPart1, pathPart2);
    }

    public File(File pathPart1, String pathPart2) {

        super(pathPart1, pathPart2);
    }

    @Override
    public File[] listFiles() {

        return convert(super.listFiles());
    }

    @Override
    public File[] listFiles(FileFilter fileFilter) {

        return convert(super.listFiles(fileFilter));
    }

    @Override
    public File[] listFiles(FilenameFilter filenameFilter) {

        return convert(super.listFiles(filenameFilter));
    }

    public boolean deleteRecursive(Progressable progressable) {

        return deleteRecursive(this, progressable);
    }

    protected boolean deleteRecursive(File file, Progressable progressable) {

        if (file.isDirectory()) {
            for (File entry : file.listFiles()) {
                entry.deleteRecursive(progressable);
            }
        }

        if (file.delete()) {
            return true;
        }

        return false;
    }

    public void deleteOnExitRecursive() {

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                deleteRecursive(null);
            }
        });
    }

    public void copy(File destination, Progressable progressable) throws FileNotFoundException, IOException {

        copy(this, destination, progressable);
    }

    protected void copy(File source, File destination, Progressable progressable) throws FileNotFoundException, IOException {

        if (source.isDirectory()) {
            destination.mkdirs();

            for (File entry : source.listFiles()) {
                entry.copy(new File(source, entry.getName()), new File(destination, entry.getName()), progressable);
            }
        } else {
            byte[] buffer = new byte[32768];

            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(destination);

            int numberOfBytes;
            while ( (numberOfBytes = in.read(buffer)) > 0) {
                out.write(buffer, 0, numberOfBytes);
            }

            in.close();
            out.close();
        }
    }

    protected double currentZipFileSize;
    protected double currentZipExtracted = 0;

    public void unzip(File destination, Progressable progress) throws ZipException, IOException {

        if (progress != null) {
            progress.setProgressStatus("Extracting " + getName());
        }

        currentZipFileSize = length();

        ZipFile zipFile = new ZipFile(this);

        for (ZipEntry zipEntry : Collections.list(zipFile.entries())) {
            extractZipEntry(zipFile, zipEntry, destination, progress);
        }
    }

    protected void extractZipEntry(ZipFile zipFile, ZipEntry zipEntry, File destination, Progressable progress) throws IOException {

        File file = new File(destination, zipEntry.getName());
        byte[] BUFFER = new byte[0xFFFF];

        if (zipEntry.isDirectory()) {
            file.mkdirs();
        } else {
            new File(file.getParent()).mkdirs();

            InputStream inputStream = null;
            OutputStream outputStream = null;

            inputStream = zipFile.getInputStream(zipEntry);
            outputStream = new FileOutputStream(file);

            for (int lenght; (lenght = inputStream.read(BUFFER)) != -1;) {
                outputStream.write(BUFFER, 0, lenght);

                currentZipExtracted += lenght;
                if (progress != null) {
                    progress.setProgressPercent((int) (currentZipExtracted / currentZipFileSize * 100));
                }
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public String read() throws FileNotFoundException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(this));

        String string = "";
        String currentLine = "";
        while ( (currentLine = reader.readLine()) != null) {
            string += currentLine + "\n";
        }
        reader.close();

        return string;
    }

    public void write(String string) throws FileNotFoundException, IOException {

        if (!getParentFile().exists()) {
            getParentFile().mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this));

        writer.write(string);

        writer.close();
    }

    public void append(String string) throws FileNotFoundException, IOException {

        if (exists()) {
            write(read() + string);
        } else {
            write(string);
        }
    }

    @Override
    public String getParent() {

        return getAbsoluteFile().getParent();
    }

    @Override
    public File getParentFile() {

        return new File(getAbsoluteFile().getParentFile().getAbsolutePath());
    }

}
