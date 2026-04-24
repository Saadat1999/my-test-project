import java.io.File;

public class Util2 {
    private static final File root = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator +"IO");

    static {
        if(!root.exists()) {
            root.mkdir();
        }
    }

    public static boolean createDirectory(String directory) {
        if(nameIsAllowed(directory)){
            File dir = new File(root, directory);
            return !dir.exists() && dir.mkdir();
        }
        return false;
    }

    public static boolean createNestedDirectories(String directories) {
        if(nameIsAllowedForNestedDirs(directories)) {
            File dirs = new File(root, directories);
            return !dirs.exists() && dirs.mkdirs();
        }
        return false;
    }

    public static void showDirectories() {
        String[] dirList = root.list();
        if(dirList!=null) {
            for(String dirName : dirList) {
                File dir = new File(root, dirName);
                if(dir.isDirectory()) {
                    System.out.println(dirName);
                }
            }
        }
    }

    public static boolean deleteEmptyDirectory(String directoryName) {
        File file = new File(root, directoryName);
        if(file.exists()) {
            return file.isDirectory() && file.delete();
        }
        return false;
    }



    private static boolean nameIsAllowed(String name) {
        if(name==null || name.isEmpty()) {
            return false;
        }
        char[] notAllowedChars = {'\\',  '/', ':', '*', '?', '"','<', '>', '|'};
        for(char c : notAllowedChars) {
            if(name.indexOf(c)>=0) {
                return false;
            }
        }
        return true;
    }

    private static boolean nameIsAllowedForNestedDirs(String name) {
        if(name==null || name.isEmpty()) {
            return false;
        }
        char[] notAllowedChars = { ':', '*', '?', '"','<', '>', '|'};
        for(char c : notAllowedChars) {
            if(name.indexOf(c)>=0) {
                return false;
            }
        }
        return true;
    }
}
