/**
 * @author :RETURN
 * @date :2022/2/9 0:06
 */
public class MultinomialCalculator {
    private final MultinomialParser multinomialParser = new MultinomialParser();

    public Result division(String dividendStr, String divisorStr) {
        Multinomial dividend = multinomialParser.parse(dividendStr);
        Multinomial divisor = multinomialParser.parse(divisorStr);
        Multinomial result = Multinomial.empty();
        Multinomial curDividend = Multinomial.empty(), curMultiplyResult = Multinomial.empty();
        Multinomial lastDividend = dividend;
        CalculateProcess process = CalculateProcess.init(dividend, divisor);


        // step.1 获取除数与被除数的最高项
        Item curPowerDividend = dividend.getTopPowerItem();
        Item curPowerDivisor = divisor.getTopPowerItem();

        // step.2 检查被除数的最高幂是否大于等于除数的最高幂
        if (curPowerDividend.getPower() < curPowerDivisor.getPower()) {
            return new Result(dividend, null, process);
        }
        while (curPowerDividend.getPower() >= curPowerDivisor.getPower()) {
            // step.3 计算第一步的商
            Item curShang = curPowerDividend.divide(curPowerDivisor);
            result.addItem(curShang);
            // step.4 通过商计算第一步的结果
            curMultiplyResult = curShang.multiply(divisor);
            // step.5 计算第二步的被除数(用第零步的被除数 - 第一步计算的结果)
            curDividend = lastDividend.sub(curMultiplyResult);
            lastDividend = curDividend;
            process.addStep(curMultiplyResult, curDividend);

            // step.6 重新获取最高项幂
            curPowerDividend = curDividend.getTopPowerItem();
        }

        return new Result(curDividend, result, process);
    }
}
