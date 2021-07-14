package System.AnalyseSource.FileStructure.Entities;

public class ClassFile implements DirectoryTreeNode{
    private String name;

    public ClassFile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
