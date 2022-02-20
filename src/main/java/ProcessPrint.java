import java.util.Queue;

/**
 * @author :RETURN
 * @date :2022/2/14 0:14
 */
public class ProcessPrint {

    private final static String SPACING = "--------------";

    public static void print(Result result) {
        CalculateProcess process = result.getCalculateProcess();

        Integer baseIntent = process.getDivisor().toString().length() + 1;
        StringBuilder baseSpacing = new StringBuilder();
        appendSpacingBuilder(baseSpacing, baseIntent);
        StringBuilder defaultSpacing = new StringBuilder(baseSpacing);
        StringBuilder content = new StringBuilder();
        Queue<CalculateProcess.Step> steps = process.getProcess();

        content.append(baseSpacing).append(result.getShang().toString()).append("\n");
        content.append(baseSpacing).append(SPACING).append("\n");
        content.append(process.getDivisor().toString()).append(")").append(process.getDividend().toString()).append("\n");
        while (true) {
            CalculateProcess.Step step = steps.poll();
            if (step == null) {
                break;
            }
            content.append(defaultSpacing).append(step.getMultiplyResult().toString()).append("\n");
            content.append(baseSpacing).append(SPACING).append("\n");
            Integer nextSpacing = step.getMultiplyResult().getTopPowerItem().toString().length();
            appendSpacingBuilder(defaultSpacing, nextSpacing);
            content.append(defaultSpacing).append(step.getCurDividend().toString()).append("\n");
        }
        System.out.print(content.toString());
    }

    private static void appendSpacingBuilder(StringBuilder sb, Integer i) {
        for (int j = 0; j < i; j++) {
            sb.append(' ');
        }
    }
}
