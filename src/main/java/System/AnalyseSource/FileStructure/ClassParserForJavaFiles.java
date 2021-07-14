package System.AnalyseSource.FileStructure;

import System.Entities.DirectedEdge;
import System.Entities.LabeledNodeWithAdjacency;
import System.Entities.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO erkennt eventuell nutzung nicht wenn Klasse immer über den kompletten Paketnamen angesprochen wird.

public class ClassParserForJavaFiles {

    public Project createProjectGraphFromFiles(List<File> files) throws Exception {
        Project p = new Project();
        createNodesFromFiles(files, p);
        createEdgesFromFiles(files, p);
        return p;
    }

    private void createEdgesFromFiles(List<File> files, Project p) throws Exception {
        for (Iterator<LabeledNodeWithAdjacency> it = p.getNodes(); it.hasNext(); ) {
            LabeledNodeWithAdjacency node = it.next();
            searchForUsesOfNode(files, p, node);
        }
    }

    private void searchForUsesOfNode(List<File> files, Project p, LabeledNodeWithAdjacency node) throws Exception {
        for (File file: files) {
            //Überspringe Datei
            if (!file.getName().equals(node.getName() + ".java")){
                LabeledNodeWithAdjacency actualNode = determineClassOfFile(p, file);
                if (actualNode == null) {
                    throw new Exception("Konnte Klasse keiner Datei zuweisen");
                }
                searchFileForUseOfNode(p, node, file, actualNode);
            }
        }
    }

    private void searchFileForUseOfNode(Project p, LabeledNodeWithAdjacency node, File file, LabeledNodeWithAdjacency actualNode) throws FileNotFoundException {
        List<String> allWordsInFile = getWordsInFile(file);
        for (String word: allWordsInFile) {
            //Falls aktuelle Klasse die gesuchte Klasse nutzt
            if (word.equals(node.getName())){
                createNewEdge(p, node, actualNode);
                return;
            }
        }
    }

    private void createNewEdge(Project p, LabeledNodeWithAdjacency node, LabeledNodeWithAdjacency actualNode) {
        DirectedEdge edge = new DirectedEdge(actualNode, node);
        if( p.addEdge(edge)) {
            node.addIncomingEdge(edge);
            actualNode.addOutgoingEdge(edge);
        }
    }

    private LabeledNodeWithAdjacency determineClassOfFile(Project p, File file) {
        for (Iterator<LabeledNodeWithAdjacency> it = p.getNodes(); it.hasNext(); ) {
            LabeledNodeWithAdjacency node = it.next();
            if (file.getName().equals(node.getName() + ".java")){
                return node;
            }
        }
        return null;
    }

    private void createNodesFromFiles(List<File> files, Project p) throws FileNotFoundException {
        for (File file : files) {
            List<String> allWordsInFile = getWordsInFile(file);
            LabeledNodeWithAdjacency node = getLabeledNodeWithClassName(allWordsInFile);
            if (node != null){
                p.addNode(node);
            }
        }
    }

    private LabeledNodeWithAdjacency getLabeledNodeWithClassName(List<String> allWordsInFile) {
        for (int i = 0; i < allWordsInFile.size(); i++) {
            if (allWordsInFile.get(i).equals("class") && i+1 < allWordsInFile.size()){
                LabeledNodeWithAdjacency node = new LabeledNodeWithAdjacency(allWordsInFile.get(i+1));
                return node;
            }
        }
        return null;
    }

    private List<String> getWordsInFile(File file) throws FileNotFoundException {
        Iterator<String> lines = getStringIterator(file);
        List<String> allWordsInFile = new ArrayList<>();
        while (lines.hasNext()){
            String line = lines.next();
            String [] words = line.split(" ");
            for (String word : words) {
                allWordsInFile.add(word.trim());
            }
        }
        return allWordsInFile;
    }

    private Iterator<String> getStringIterator(File file) throws FileNotFoundException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader= new BufferedReader(isr);
        Iterator<String> lines = reader.lines().iterator();
        return lines;
    }
}


