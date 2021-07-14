package System.AnalyseSource.FileStructure.Entities;

import org.junit.Test;

import java.util.Iterator;


public class TestDirectoryTree {

    @Test
    public void rootTest(){
        DirectoryTree dt = new DirectoryTree();
        assert dt.getActualFolder().getName().equals("root");
    }

    @Test
    public void folderTest(){
        DirectoryTree dt = new DirectoryTree();
        dt.getActualFolder().addSon(new Folder("folder"));
        assert dt.getFoldersInActualFolder().hasNext();
    }

    @Test
    public void fileTest(){
        DirectoryTree dt = new DirectoryTree();
        dt.getActualFolder().addSon(new ClassFile("file"));
        assert dt.getClassFilesInActualFolder().hasNext();
    }

    @Test
    public void nametest(){
        DirectoryTree dt = new DirectoryTree();
        dt.getActualFolder().addSon(new Folder("folder"));
        dt.getActualFolder().addSon(new ClassFile("file"));
        Iterator<ClassFile> files = dt.getClassFilesInActualFolder();
        Iterator<Folder> folderIterator = dt.getFoldersInActualFolder();
        assert files.hasNext();
        assert folderIterator.hasNext();
        assert (files.next().getName().equals("file"));
        assert (folderIterator.next().getName().equals("folder"));

        Iterator<Folder> it = dt.getFoldersInActualFolder();
        Folder f = it.next();
        f.addSon(new ClassFile("test"));
        dt.setActualFolderToRoot();
        assert (dt.contains("test"));

    }

    @Test
    public void testPointerSetting(){
        DirectoryTree dt = new DirectoryTree();
        dt.getActualFolder().addSon(new Folder("folder"));
        for (Iterator<Folder> it = dt.getFoldersInActualFolder(); it.hasNext(); ) {
            Folder f = it.next();
            if (f.getName().equals("folder")) {
                dt.setActualFolder(f);
            }
        }
        assert dt.getActualFolder().getName().equals("folder");
        dt.setActualFolderToRoot();
        assert dt.getActualFolder().getName().equals("root");
    }


}
