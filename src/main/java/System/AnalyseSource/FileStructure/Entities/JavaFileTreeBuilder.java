package System.AnalyseSource.FileStructure.Entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaFileTreeBuilder {
    private Path sourceDirectory;



    public JavaFileTreeBuilder(Path sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }


    public DirectoryTree walkFileTree() throws IOException {
        JavaFileVisitor fileVisitor = new JavaFileVisitor();
        Files.walkFileTree(sourceDirectory, fileVisitor);
        return fileVisitor.getFileTree();
    }
}
