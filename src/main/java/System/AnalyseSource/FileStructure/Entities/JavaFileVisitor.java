package System.AnalyseSource.FileStructure.Entities;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

class JavaFileVisitor implements FileVisitor<Path> {
    private DirectoryTree directoryTree;

    JavaFileVisitor() {
        this.directoryTree = new DirectoryTree();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //TODO Test schreiben
        directoryTree.getActualFolder().addSon(new Folder(dir.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //TODO Filter, so dass nur .java Dateien aufgenommen werden. Test Schreiben.
        directoryTree.getActualFolder().addSon(new ClassFile(file.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        //TODO Test schreiben
        Folder folder = getFolderWithNameFromFIleTree(dir);
        if (folder != null && exc == null){
            directoryTree.setActualFolder(folder);
        }
        return FileVisitResult.CONTINUE;
    }

    private Folder getFolderWithNameFromFIleTree(Path dir) {
        //TODO Test schreiben.
        for (Iterator<Folder> it = directoryTree.getFoldersInActualFolder(); it.hasNext(); ) {
            Folder folder = it.next();
            if (folder.getName().equals(dir.getFileName().toString())){
                return folder;
            }
        }
        return null;
    }

    public DirectoryTree getFileTree() {
        return this.directoryTree;
    }
}
