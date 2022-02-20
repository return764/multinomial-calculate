import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :RETURN
 * @date :2022/2/13 23:10
 */
public class CalculateProcess {
    private final Multinomial dividend;
    private final Multinomial divisor;
    private final Queue<Step> process;

    public CalculateProcess(Multinomial dividend, Multinomial divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.process = new LinkedList<>();
    }


    public static CalculateProcess init(Multinomial dividend, Multinomial divisor) {
        return new CalculateProcess(dividend, divisor);
    }

    public void addStep(Multinomial m1, Multinomial m2) {
        this.process.offer(new Step(m1, m2));
    }

    public Multinomial getDividend() {
        return dividend;
    }

    public Multinomial getDivisor() {
        return divisor;
    }

    public Queue<Step> getProcess() {
        return new LinkedList<>(process);
    }

    public static class Step {
        private final Multinomial multiplyResult;
        private final Multinomial curDividend;

        public Step(Multinomial multiplyResult, Multinomial curDividend) {
            this.multiplyResult = multiplyResult;
            this.curDividend = curDividend;
        }

        public Multinomial getMultiplyResult() {
            return multiplyResult;
        }

        public Multinomial getCurDividend() {
            return curDividend;
        }
    }
}
