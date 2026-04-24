import java.io.File;

public class Util {
    private static final File root = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator +"IO");

    static {
        if(!root.exists()) {
            if(!root.mkdir()) {
                System.out.println("Directory could not be created.");
            }
        }
    }

    public static void createDirectory(String directory) {
        if(nameIsAllowed(directory)){
            File dir = new File(root, directory);
            if(!dir.exists()) {
                if(!dir.mkdir()) {
                    System.out.println("Directory could not be created.");
                }
            } else {
                System.out.println("This destination already contains a directory named " + directory);
            }
        }
    }

    public static void createNestedDirectories(String directories) {
        if(nameIsAllowedForNestedDirs(directories)) {
            File dirs = new File(root, directories);
            if(!dirs.exists()) {
                if(!dirs.mkdirs()) {
                    System.out.println("Directories could not be created.");
                }
            } else {
                System.out.println("Directories already exist.");
            }
        }
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
        } else {
            System.out.println("Couldn't list directories.");
        }

    }

    public static void deleteEmptyDirectory(String directoryName) {
        File directory = new File(root, directoryName);
        if(directory.exists()) {
            if(directory.isDirectory()) {
                if(!directory.delete()) {
                    System.out.println("Couldn't delete directory "+ directory + ". It may not be empty.");
                }
            } else {
                System.out.println(directory + " is not a directory.");
            }
        } else {
            System.out.println("Directory " + directory.getName() + " doesn't exist.");
        }
    }



    private static boolean nameIsAllowed(String name) {
        if(name==null || name.isEmpty()) {
            System.out.println("Name is not valid");
            return false;
        }
        char[] notAllowedChars = {'\\',  '/', ':', '*', '?', '"','<', '>', '|'};
        for(char c : notAllowedChars) {
            if(name.indexOf(c)>=0) {
                System.out.println("Directory name can't contain any of the following: \\ / : * ? \" < > |");
                return false;
            }
        }
        return true;
    }

    private static boolean nameIsAllowedForNestedDirs(String name) {
        if(name==null || name.isEmpty()) {
            System.out.println("Name is not valid");
            return false;
        }
        char[] notAllowedChars = { ':', '*', '?', '"','<', '>', '|'};
        for(char c : notAllowedChars) {
            if(name.indexOf(c)>=0) {
                System.out.println("Directory name can't contain any of the following: : * ? \" < > |");
                return false;
            }
        }
        return true;
    }
}
