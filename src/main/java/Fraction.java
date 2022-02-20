import java.util.Objects;

/**
 * @author :RETURN
 * @date :2022/2/11 11:17
 */
public class Fraction {
    private Integer a;
    private Integer b;
    private String result;

    public Fraction(Integer a, Integer b) {
        this.a = a;
        this.b = b;
        if (b == 0) {
            throw new RuntimeException("分母不能为0");
        }
        appointment();
    }

    public static Fraction create(Integer a, Integer b) {
        return new Fraction(a, b);
    }

    public static Fraction create(String s) {
        String[] split = s.split("/", 2);

        int a = Integer.parseInt(split[0]);
        if (split.length < 2) {
            return new Fraction(a, 1);
        }
        int b = Integer.parseInt(split[1]);

        return new Fraction(a, b);
    }

    private void appointment() {
        if (a == 0 || b == 1) {
            return; // 如果分子是0或分母是1就不用约分了
        }
        Integer gcd = gcd(a,b);
        this.a /= gcd;
        this.b /= gcd;
    }

    private static Integer gcd(Integer a, Integer b) { // 用辗转相除法求最大公约数
        return b==0? a: gcd(b,a%b);
    }

    @Override
    public String toString() {
        if (!Objects.isNull(result) && !result.isEmpty()) {
            return result;
        }
        float c = (float) this.a / (float) this.b;

        if (Math.floor(c) == c) {
            result = Integer.valueOf((int) c).toString();
            return result;
        }
        if ((this.a < 0 && this.b < 0) || (this.a > 0 && this.b > 0)) {
            result = this.a + "/" + this.b;
        }
        if ((this.a > 0 && this.b < 0) || (this.a < 0 && this.b > 0)) {
            result = "-" + Math.abs(this.a) + "/" + Math.abs(this.b);
        }

        return result;
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public Fraction multiply(Fraction f1) {
        return create(this.a * f1.a, this.b * f1.b);
    }

    public boolean isPositive() {
        boolean ab = this.a > 0;
        boolean bb = this.b > 0;
        return ab == bb;
    }

    public Fraction sub(Fraction f1) {
        return Fraction.create(this.a*f1.b - this.b*f1.a, this.b * f1.b);
    }

    public boolean isZero() {
        return a == 0 || b == 0;
    }
}
