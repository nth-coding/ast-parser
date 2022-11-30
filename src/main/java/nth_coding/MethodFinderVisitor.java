package nth_coding;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.io.IOException;

import static nth_coding.ASTParserTest.output;

public class MethodFinderVisitor extends ASTVisitor {
    private String getMethodInfo(IMethodBinding methodBinding) throws IOException {
        return "\nMethod: " + methodBinding +
                "\n-> From class: " + methodBinding.getDeclaringClass().getName();
    }

    public boolean visit(MethodInvocation node) {
        IMethodBinding methodBinding = node.resolveMethodBinding();
        if (methodBinding == null) return false;
        try {
//            System.out.println(getMethodInfo(methodBinding));
            output.add(getMethodInfo(methodBinding));
        } catch (IOException e) {
//            System.out.println("err");
        }
        return true;
    }
}
