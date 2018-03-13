package stackowerflow;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class Java8_filter {


    private static final String FILE_NAME_FILTER = "1";
    private static final String HOME_FOLDER_FILTER = "home";
    private static final String VAR_FOLDER_FILTER = "var";
    private static final String BIN_FOLDER_FILTER = "bin";

    @Test
    public void test() {
        File f1 = new File("1", "txt");
        File f2 = new File("1", "json");
        File f3 = new File("1", "yml");
        File f4 = new File("4", "doc");

        Folder folder1 = new Folder(HOME_FOLDER_FILTER, asList(f1, f2));
        Folder folder2 = new Folder(VAR_FOLDER_FILTER, asList(f2, f3));
        Folder folder3 = new Folder(BIN_FOLDER_FILTER, asList(f3, f4));
        List<Folder> folders = asList(folder1, folder2, folder3);

        List<File> files = folders.stream()
                .filter(folder -> folder.getName().equals(HOME_FOLDER_FILTER))
                .map(Folder::getFiles) // no helper method
                .flatMap(List::stream)
                .filter(file -> file.getName().equals(FILE_NAME_FILTER))
                .collect(Collectors.toList());
        Assert.assertEquals(2, files.size());
    }

    public class Folder {
        String name;
        List<File> files;

        Folder(String name, List<File> files) {
            this.files = files;
            this.name = name;
        }

        String getName() {
            return name;
        }

        List<File> getFiles() {
            return files;
        }
    }

    public class File {
        String name;
        String type;

        File(String name, String type) {
            this.name = name;
            this.type = type;
        }

        String getName() {
            return name;
        }

        String getType() {
            return type;
        }
    }


}
