package nth_coding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;

public class ASTParserTest {
    public static List<String> output = new ArrayList<>();
    public static String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();
        return fileData.toString();
    }

    public static CompilationUnit createCU(String filename, String path) throws IOException {
        String fileName = filename + ".java";
        String str = readFileToString(path);

        ASTParser parser = ASTParser.newParser(AST.JLS18);
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        parser.setBindingsRecovery(true);

        Hashtable<String, String> options = JavaCore.getOptions();
        parser.setCompilerOptions(options);

        parser.setUnitName(fileName);

        String[] sources = {"src"};
        String[] classpath = {"target/classes"};

        parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
        parser.setSource(str.toCharArray());

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        return cu;
    }

    public static void parse(CompilationUnit cu) {
        if (cu.getAST().hasBindingsRecovery()) {
            System.out.println("Binding activated.\n----------------");
        }

        MethodFinderVisitor v = new MethodFinderVisitor();
        cu.accept(v);
    }

    public String getOutputAsString() {
        StringBuilder str = new StringBuilder();
        for (String s : output) {
            str.append(s);
        }
        return str.toString();
    }

    public void getOutputAsFile(String filename) throws IOException {
        OutputStream os = Files.newOutputStream(Paths.get(filename));
        for (String op : output) {
            os.write(op.getBytes());
        }
        os.flush();

        //close the stream
        os.close();
    }

    public ASTParserTest(String filename, String path) throws IOException {
        CompilationUnit compilationUnit = createCU(filename, path);
        parse(compilationUnit);
    }
}