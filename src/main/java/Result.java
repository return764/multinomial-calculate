/**
 * @author :RETURN
 * @date :2022/2/9 0:08
 */
public class Result {
    private Multinomial remainder;
    private Multinomial shang;
    private CalculateProcess process;

    public Result(Multinomial remainder, Multinomial shang, CalculateProcess process) {
        this.remainder = remainder;
        this.shang = shang;
        this.process = process;
    }

    public Multinomial getRemainder() {
        return remainder;
    }

    public void setRemainder(Multinomial remainder) {
        this.remainder = remainder;
    }

    public Multinomial getShang() {
        return shang;
    }

    public void setShang(Multinomial shang) {
        this.shang = shang;
    }

    public CalculateProcess getCalculateProcess() {
        return process;
    }
}
