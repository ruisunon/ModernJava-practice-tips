package io.corejava.files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RenameFile
{
  public static void listFiles(String dir) {
    try {
      Files.find(Paths.get(dir), Integer.MAX_VALUE,
          (filePath, fileAttr) -> fileAttr.isRegularFile())
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void rename(String dirName){
    File dir= new File(dirName);
    File[] filesInDir=dir.listFiles();
    for(File file:filesInDir) {
      String name = file.getName();
      String newName = "my_file_"  + ".pdf";
      String newPath = dirName + "\\" + newName;
      file.renameTo(new File(newPath));
      System.out.println(name + " changed to " + newName);
    }
  }

  public static void renameFiles(String dir) {
    try {
      try (Stream<Path> stream = Files.find(Paths.get(dir), 6,
          (path, attr) -> String.valueOf(path).contains("-"))) {

           stream.map(String::valueOf).forEach(item -> {
              try {

                Files.move(new File(item).toPath(), new File(changeName(item)).toPath());
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
        );
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void renameFiles(String dir, String replace, String replaceBy) {
    try {
      try (Stream<Path> stream = Files.find(Paths.get(dir), 4,
          (path, attr) -> String.valueOf(path).contains("-"))) {
           stream.map(String::valueOf).forEach(item -> {
              try {
                Files.move(new File(item).toPath(), new File(item.replace(replace, replaceBy)).toPath());
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
        );
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Remove all space characters
  public static String removespaces(String input) {
    return input.replaceAll(" ", "");
  }

  // Remove all space characters
  public static String changeName(String input) {
    return input.replaceAll("-", "_").replaceAll(".src", "");
  }
  public static void main(String[] args)
  {
    //working folder
    //C:\zoz.Solutions\leetcode.solutions\leetcode-java-solutions\src\main\java\Easy
    String dir = "C:\\zoz.Solutions\\leetcode.solutions\\leetcode-solution-java\\src\\main\\java\\net\\leetcode"
        + "\\questions\\array\\Java";
    //C:\zoz.Solutions\leetcode.solutions\leetcode-java-solutions\src\main\java\io\coding\concurrency
    //recursively list files before renaming
    listFiles(dir);
    //rename files - replace text in the name with song.text
    renameFiles(dir);
    //recursively list files after renaming
    listFiles(dir);
  }
}