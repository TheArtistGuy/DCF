package System.AnalyseSource.FileStructure.Controller;

import System.AnalyseSource.FileStructure.Entities.DirectoryTree;

import java.io.File;

public class DirectoryTreeC {
    DirectoryTree directoryTree;

    public DirectoryTreeC(DirectoryTree directoryTree) {
        this.directoryTree = directoryTree;
    }

    public DirectoryTree getDirectoryTree() {
        return directoryTree;
    }


    public void createFileTree(File file) {
        //TODO
    }
}
