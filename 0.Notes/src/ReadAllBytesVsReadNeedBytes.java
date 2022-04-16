import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Date;
import java.util.Iterator;

/* 
task1530
*/

public class ReadAllBytesVsReadNeedBytes {

    public static void main(String[] args) throws IOException {

        Date beforeFirstStart = new Date();

        try (FileInputStream reader = new FileInputStream("d:\\test.file");
             ByteArrayInputStream stream = new ByteArrayInputStream(reader.readAllBytes())) {

            Date afterReadFile = new Date();

            System.out.println(new String(bytesToChars(stream, 10)));
            Date afterToCharsReadNeedBytes = new Date();

            System.out.println("Время чтения файла 470МБ и перевод его в байтовый массив, мс - " + (afterReadFile.getTime() - beforeFirstStart.getTime()));
            System.out.println("Время чтения первых 10 байт и перевод в символы, мс - " + (afterToCharsReadNeedBytes.getTime() - afterReadFile.getTime()));
        }

        System.out.printf("-------------\n");

        Date beforeSecondStart = new Date();

        try (FileInputStream reader = new FileInputStream("d:\\test.file");
             ByteArrayInputStream stream = new ByteArrayInputStream(reader.readAllBytes())) {

            Date afterReadFile = new Date();

            System.out.println(new String(bytesToCharsReadAllBytes(stream, 10)));
            Date afterToCharsReadAllBytes = new Date();

            System.out.println("Время чтения файла 470МБ и перевод его в байтовый массив, мс - " + (afterReadFile.getTime() - beforeSecondStart.getTime()));
            System.out.println("Время чтения всего массива и перевод 10 байт в символы, мс - " + (afterToCharsReadAllBytes.getTime() - afterReadFile.getTime()));
        }
    }

    public static char[] bytesToChars(ByteArrayInputStream stream, int n) {
        char[] result = new char[n];
        int len = Math.min(stream.available(), n);
        byte[] bytes = new byte[len];
        stream.read(bytes, 0, len);

        for (int i = 0; i < len; i++) {
            result[i] = (char) bytes[i];
        }
        return result;
    }

    public static char[] bytesToCharsReadAllBytes(ByteArrayInputStream stream, int n) {
        char[] result = new char[n];
        byte[] bytes = stream.readAllBytes();
        int minLength = Math.min(n, bytes.length);
        for (int i = 0; i < minLength; i++) {
            result[i] = (char) bytes[i];
        }
        return result;
    }

    DirectoryStream<Path> directoryStream = new DirectoryStream<Path>() {
        @Override
        public Iterator<Path> iterator() {
            return null;
        }

        @Override
        public void close() throws IOException {

        }
    };
}
