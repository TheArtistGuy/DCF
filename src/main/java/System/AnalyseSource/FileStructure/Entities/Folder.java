package System.AnalyseSource.FileStructure.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Folder implements DirectoryTreeNode{
    private String name;
    private List<Folder> foldersContained;
    private List<ClassFile> filesContained;

    public Folder(String name) {
        this.name = name;
        this.foldersContained = new ArrayList<>();
        this.filesContained = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    Iterator<Folder> getFoldersContained(){
        return this.foldersContained.iterator();
    }
    Iterator<ClassFile> getFilesContained(){
        return this.filesContained.iterator();
    }


    public void addSon(DirectoryTreeNode node){
        if (node instanceof ClassFile) filesContained.add((ClassFile) node);
        if (node instanceof Folder) foldersContained.add((Folder) node);
    }
}
