import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File("files");
        dir.mkdir();
        File file = new File("files/myfile.txt");
        file.createNewFile();


        File newFile = new File("files/newfile.txt");
        newFile.createNewFile();


        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        FileOutputStream fos = new FileOutputStream(newFile, true);
        fos.write(bytes);
    }
}
