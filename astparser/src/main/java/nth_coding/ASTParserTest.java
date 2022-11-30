package nth_coding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;

public class ASTParserTest {
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

    public static void parse(String filename) throws IOException {
        String fileName = filename + ".java";
        String path = "src/main/java/nth_coding.testcase/" + fileName;
        String str = readFileToString(path);

        ASTParser parser = ASTParser.newParser(AST.JLS18);
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        parser.setBindingsRecovery(true);

        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);

        String unitName = fileName;
        parser.setUnitName(unitName);

        String[] sources = {"src"};
        String[] classpath = {"target/classes"};

        parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
        parser.setSource(str.toCharArray());

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        if (cu.getAST().hasBindingsRecovery()) {
            System.out.println("Binding activated.\n----------------");
        }

        TypeFinderVisitor v = new TypeFinderVisitor();
        cu.accept(v);
    }
    public static void main(String[] args) throws IOException {
        parse("Apple");
    }
}

class TypeFinderVisitor extends ASTVisitor {
    private String getMethodInfo(IMethodBinding methodBinding) throws IOException {

        StringBuilder str = new StringBuilder("\nMethod: " + methodBinding +
                "\n-> From class: " + methodBinding.getDeclaringClass().getName());
        return str.toString();
    }

    public boolean visit(MethodInvocation node) {
        IMethodBinding methodBinding = node.resolveMethodBinding();
        if (methodBinding == null) return false;
        try {
            System.out.println(getMethodInfo(methodBinding));
        } catch (IOException e) {
//            System.out.println("err");
        }
        return true;
    }
}

