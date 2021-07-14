package System.AnalyseSource.FileStructure.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementiert einen Baum aus Vrezeichnissen und Datein, welche zur Ablage von Namen von Klassen und dem Festhalten der Ordner-Struktur dient.
 */

public class DirectoryTree {
    private Folder root;
    private Folder actualFolder;

    public DirectoryTree() {
        this.root = new Folder("root");
        this.actualFolder = this.root;
    }

    public Folder getActualFolder() {
        return actualFolder;
    }

    public void setActualFolder(Folder actualFolder) {
        this.actualFolder = actualFolder;
    }

    public Iterator<String> getActualFolderEntryNames() {
        List<String> names = new ArrayList<>();
        for (Iterator<Folder> it = actualFolder.getFoldersContained(); it.hasNext(); ) {
            DirectoryTreeNode node = it.next();
            names.add(node.getName());
        }
        for (Iterator<ClassFile> it = actualFolder.getFilesContained(); it.hasNext(); ) {
            DirectoryTreeNode node = it.next();
            names.add(node.getName());
        }
        return names.iterator();
    }

    public Iterator<Folder> getFoldersInActualFolder() {
        return actualFolder.getFoldersContained();
    }


    public Iterator<ClassFile> getClassFilesInActualFolder() {
        return actualFolder.getFilesContained();
    }

    public void setActualFolderToRoot() {
        this.actualFolder = this.root;
    }

    public boolean contains(String fileStructure) {
        return containsName(root, fileStructure);
    }

    private boolean containsName(Folder folder, String name) {
        if (folder.getName().equals(name)) return true;
        for (Iterator<ClassFile> it = folder.getFilesContained(); it.hasNext(); ) {
            ClassFile file = it.next();
            if (file.getName().equals(name)) return true;
        }
        for (Iterator<Folder> it = folder.getFoldersContained(); it.hasNext(); ) {
            Folder f = it.next();
            if (containsName(f, name)) return true;
        }
        return false;
    }
}