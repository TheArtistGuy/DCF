package System.AnalyseSource.FileStructure.Controller;

import System.AnalyseSource.FileStructure.Entities.DirectoryTree;
import org.junit.Test;

import java.io.File;

public class TestDirectoryTreeC {
    @Test
    public void testBuildFileStructure(){
        DirectoryTreeC dtc = new DirectoryTreeC(new DirectoryTree());

        dtc.createFileTree(new File("..\\..\\..\\..\\"));
        assert dtc.getDirectoryTree().contains("FileStructure");
    }
}
