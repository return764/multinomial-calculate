import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author :RETURN
 * @date :2022/2/8 23:27
 */
public class Tests {
    @Test
    public void calculateTest() {
        MultinomialCalculator calculator = new MultinomialCalculator();
        Result result = calculator.division("x^3-2", "x+3");
        assertEquals("-29", result.getRemainder().toString());
        assertEquals("x^2-3x+9", result.getShang().toString());
    }

    @Test
    public void multinomialParserTest() {
        MultinomialParser parser = new MultinomialParser();
        Multinomial multinomial = parser.parse("x^2-3x+9");
        List<Item> items = multinomial.getItems();
        assertEquals(Item.create(1, 2), items.get(0));
        assertEquals(Item.create(-3, 1), items.get(1));
        assertEquals(Item.create(9, 0), items.get(2));

        Multinomial m2 = parser.parse("1/2x^2-3x+5/9");
        assertEquals("1/2x^2-3x+5/9", m2.toString());
    }

    @Test
    public void itemParserTest() {
        MultinomialParser parser = new MultinomialParser();
        Item item = parser.parseItem("x^2");
        Item item1 = parser.parseItem("-x");
        Item item2 = parser.parseItem("+9");
        Item item3 = parser.parseItem("5");
        Item item4 = parser.parseItem("+9x^3");
        Item item5 = parser.parseItem("-7x^2");
        Item item6 = parser.parseItem("3x");
        Item item7 = parser.parseItem("-8x");

        assertEquals("1", item.getCoefficient().toString());
        assertEquals(2, item.getPower());

        assertEquals("-1", item1.getCoefficient().toString());
        assertEquals(1, item1.getPower());

        assertEquals("9", item2.getCoefficient().toString());
        assertEquals(0, item2.getPower());

        assertEquals("5", item3.getCoefficient().toString());
        assertEquals(0, item3.getPower());

        assertEquals("9", item4.getCoefficient().toString());
        assertEquals(3, item4.getPower());

        assertEquals("-7", item5.getCoefficient().toString());
        assertEquals(2, item5.getPower());

        assertEquals("3", item6.getCoefficient().toString());
        assertEquals(1, item6.getPower());

        assertEquals("-8", item7.getCoefficient().toString());
        assertEquals(1, item7.getPower());
    }

    @Test
    public void getTopPowerItemTest() {
        List<Item> list = Arrays.asList(Item.create(1, 2),
                Item.create(-3, 1),
                Item.create(9, 0));

        Multinomial multinomial = Multinomial.create(new ArrayList<>(list));

        Item item = multinomial.getTopPowerItem();
        assertEquals(Item.create(1, 2), item);
    }

    @Test
    public void ignoreItemTest() {
        List<Item> list = Arrays.asList(Item.create(1, 2),
                Item.create(9, 0));

        Multinomial multinomial = Multinomial.create(new ArrayList<>(list));

        List<Item> items = multinomial.getItems();
        assertEquals(Item.create(1, 2), items.get(0));
        assertEquals(Item.create(0, 1), items.get(1));
        assertEquals(Item.create(9, 0), items.get(2));
    }

    @Test
    public void fractionTest() {
        Fraction fraction = new Fraction(9, 2);
        assertEquals("9/2", fraction.toString());
        Fraction fraction1 = new Fraction(3, 9);
        assertEquals("1/3", fraction1.toString());
        Fraction fraction2 = new Fraction(2, 3);
        assertEquals("2/3", fraction2.toString());
        Fraction fraction3 = new Fraction(3, 3);
        assertEquals("1", fraction3.toString());
    }

    @Test
    public void twoFractionSignTest() {
        Fraction f1 = Fraction.create(-1, 2);
        Fraction f2 = Fraction.create(1, -2);
        Fraction f3 = Fraction.create(-1, -2);
        Fraction f4 = Fraction.create(1, 2);
        assertFalse(f1.isPositive());
        assertFalse(f2.isPositive());
        assertTrue(f3.isPositive());
        assertTrue(f4.isPositive());
    }

    @Test
    public void processTest() {
        MultinomialCalculator calculator = new MultinomialCalculator();
        Result result = calculator.division("x^3-2", "x+3");
        CalculateProcess process = result.getCalculateProcess();

//        assertEquals("x^3+3x^2", process.get(0).toString());
//        assertEquals("-3x^2-2", process.get(1).toString());
//        assertEquals("3x^2-9x", process.get(1).toString());
//        assertEquals("9x-2", process.get(2).toString());
//        assertEquals("9x+27", process.get(2).toString());
//        assertEquals("-29", process.get(3).toString());
    }

    @Test
    public void itemDivisionTest() {
        Item dividend = Item.create(2, 3);
        Item divisor = Item.create(3, 1);

        Item i = dividend.divide(divisor);
        assertEquals("2/3x^2", i.toString());
    }

    @Test
    public void multinomialPrintTest() {
        MultinomialParser parser = new MultinomialParser();
        Multinomial m1 = parser.parse("x^3+3x^2");
        Multinomial m2 = parser.parse("-x^4+3x^2-11x");

        assertEquals("x^3+3x^2", m1.toString());
        assertEquals("-x^4+3x^2-11x", m2.toString());
    }

    @Test
    public void itemMultiplyMultinomialTest() {
        MultinomialParser parser = new MultinomialParser();
        Item i1 = parser.parseItem("x^2");
        Multinomial m1 = parser.parse("x+3");

        Multinomial m2 = i1.multiply(m1);
        assertEquals("x^3+3x^2", m2.toString());
    }

    @Test
    public void multinomialSubTest() {
        MultinomialParser parser = new MultinomialParser();
        Multinomial m1 = parser.parse("x^3+3/4");
        Multinomial m2 = parser.parse("x^3+3x^2+2/3");

        Multinomial rs = m1.sub(m2);
        assertEquals("-3x^2+1/12", rs.toString());
    }

}
