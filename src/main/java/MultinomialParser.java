import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :RETURN
 * @date :2022/2/9 0:14
 */
public class MultinomialParser {
    private static final Pattern SPLIT_RE = Pattern.compile("(([+-]?[0-9/]*x\\^[0-9]+)|([+-]?[0-9/]*x)|([+-]x\\^[0-9]+)|([+-][0-9/]+)|([0-9/]+)|(x\\^[0-9]+))|(x)");
    private static final Pattern ITEM_RE = Pattern.compile("([+-]?[0-9/]*)?([x]?)[\\^]?([0-9]*)");

    public Multinomial parse(final String s) {
        StringBuilder mStr = new StringBuilder(s);
        List<Item> items = new ArrayList<>();
        Matcher matcher = SPLIT_RE.matcher(mStr.toString());

//        if (!matcher.matches()) {
//            throw new RuntimeException("表达式错误");
//        }
        while (matcher.find()) {
            items.add(parseItem(matcher.group()));
        }
        return Multinomial.create(items);
    }

    public Item parseItem(String itemStr) {
        Matcher matcher = ITEM_RE.matcher(itemStr);
        matcher.find();
        String coefficientGroup = matcher.group(1);
        String unknownGroup = matcher.group(2);
        String powerGroup = matcher.group(3);

        Fraction coefficient = Fraction.create(1, 1);
        int power = 1;

        if (!coefficientGroup.isEmpty()) {
            if ("-".equals(coefficientGroup)) {
                coefficient = Fraction.create(-1, 1);
            } else {
                coefficient = Fraction.create(coefficientGroup);
            }
        }

        if (!powerGroup.isEmpty()) {
            power = Integer.parseInt(powerGroup);
        }else if (unknownGroup.isEmpty()) {
            power = 0;
        }

        return Item.create(coefficient, power);
    }
}
