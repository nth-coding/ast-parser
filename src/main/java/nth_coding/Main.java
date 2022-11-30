package nth_coding;

public class Main {
    public static void main(String[] args) {
        try {
            ASTParserTest astParserTest = new ASTParserTest("Apple", "src/main/java/nth_coding/testcase/Apple.java");

            astParserTest.getOutputAsFile("output.txt");
            System.out.println(astParserTest.getOutputAsString());

        } catch (Exception e) {
            System.out.println("err");
        }
    }
}
