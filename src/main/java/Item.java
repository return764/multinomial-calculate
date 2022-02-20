import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author :RETURN
 * @date :2022/2/9 11:49
 */
public class Item {
    private Fraction coefficient;
    private String unknown;
    private int power;

    public Item(Fraction coefficient, String unknown, int power) {
        this.coefficient = coefficient;
        this.unknown = unknown;
        this.power = power;
    }

    public static Item create(int coefficient, int power) {
        return create(coefficient, 1, power);
    }

    public static Item create(int a, int b, int power) {
        return new Item(new Fraction(a, b), "x", power);
    }

    public static Item create(Fraction a, int power) {
        return new Item(a, "x", power);
    }

    public Fraction getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Fraction coefficient) {
        this.coefficient = coefficient;
    }

    public String getUnknown() {
        return unknown;
    }

    public void setUnknown(String unknown) {
        this.unknown = unknown;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if ("0".equals(coefficient.toString())) {
            return "";
        }

        if ("-1".equals(coefficient.toString())) {
            if (power != 0) {
                sb.append("-");
            } else {
                sb.append("-1");
            }
        } else if ("1".equals(coefficient.toString())) {
            if (power == 0) {
                sb.append(coefficient);
            }
        } else {
            sb.append(coefficient);
        }

        if (power == 1) {
            sb.append(unknown);
        } else if (power != 0) {
            sb.append(unknown).append("^").append(power);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (power != item.power) {
            return false;
        }
        if (!Objects.equals(coefficient.toString(), item.coefficient.toString())) {
            return false;
        }
        return Objects.equals(unknown, item.unknown);
    }

    @Override
    public int hashCode() {
        int result = coefficient != null ? coefficient.hashCode() : 0;
        result = 31 * result + (unknown != null ? unknown.hashCode() : 0);
        result = 31 * result + power;
        return result;
    }

    public Item divide(Item divisor) {
        Fraction curCof = this.getCoefficient();
        Fraction otherCof = divisor.getCoefficient();
        return Item.create(curCof.getA()*otherCof.getB(),
                otherCof.getA()*curCof.getB(),
                this.getPower() - divisor.getPower());
    }

    public Item multiply(Item i1) {
        return Item.create(this.getCoefficient().multiply(i1.getCoefficient()), this.getPower() + i1.getPower());
    }

    public Multinomial multiply(Multinomial multinomial) {
        List<Item> items = multinomial.getItems();
        List<Item> newItems = new ArrayList<>();
        for (Item i : items) {
            newItems.add(this.multiply(i));
        }
        return Multinomial.create(newItems);
    }

    public Item samePowerSub(Item i1) {
        return Item.create(this.getCoefficient().sub(i1.getCoefficient()), i1.getPower());
    }
}
