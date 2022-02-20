/**
 * @author :RETURN
 * @date :2022/2/8 23:23
 */
public class Starter {
    public static void main(String[] args) {
        MultinomialCalculator calculator = new MultinomialCalculator();
        Result result = calculator.division("-x^4-x", "-x");
        ProcessPrint.print(result);
    }
}
