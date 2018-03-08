package stackowerflow;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class Java8_filter {


    private static final String FILE_NAME_FILTER = "1";

    @Test
    public void test() {
        File f1 = new File("1", "txt");
        File f2 = new File("1", "json");
        File f3 = new File("1", "yml");
        File f4 = new File("4", "doc");

        Folder folder1 = new Folder(asList(f1, f2));
        Folder folder2 = new Folder(asList(f2, f3));
        Folder folder3 = new Folder(asList(f3, f4));
        List<Folder> folders = asList(folder1, folder2, folder3);

        List<File> files = folders.stream()
                .map(Folder::getFiles)
                .flatMap(List::stream)
                .filter(file -> file.getName().equals(FILE_NAME_FILTER))
                .collect(Collectors.toList());
        Assert.assertEquals(5, files.size());
        files.forEach(file -> Assert.assertEquals(FILE_NAME_FILTER, file.getName()));
    }

    public class Folder {
        String name;
        List<Folder> folders;
        List<File> files;

        public Folder(List<File> files) {
            this.files = files;
        }

        public List<Folder> getFolders() {

            return folders;
        }

        public String getName() {
            return name;
        }

        public List<File> getFiles() {
            return files;
        }
    }

    public class File {
        String name;
        String type;

        public File(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }


}
