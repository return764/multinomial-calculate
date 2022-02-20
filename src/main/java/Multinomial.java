import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author :RETURN
 * @date :2022/2/9 11:49
 */
public class Multinomial {
    private final List<Item> inner;

    public Multinomial(List<Item> items) {
        checkAndConstructInner(items);
        this.inner = items;
    }

    public static Multinomial create(List<Item> items) {
        return new Multinomial(items);
    }

    public static Multinomial empty() {
        return new Multinomial(new ArrayList<>());
    }

    // 补齐缺项
    private void checkAndConstructInner(List<Item> items) {
        if (items.isEmpty()) {
            return;
        }

        Item item = getTopPowerItem(items);
        while ("".equals(item.toString())) {
            items.remove(item);
            try {
                item = getTopPowerItem(items);
            } catch (RuntimeException e) {
                item = Item.create(0, 0);
                break;
            }

        }
        int topPower = item.getPower();

        List<Integer> powerAtItems = items.stream().map(Item::getPower).collect(Collectors.toList());

        for (int i = topPower; i >= 0; i--) {
            if (!powerAtItems.contains(i)) {
                items.add(Item.create(0, i));
            }
        }
        sort(items);
    }

    public List<Item> getItems() {
        return inner;
    }

    private void sort(List<Item> inner) {
        inner.sort((i1, i2) -> i2.getPower() - i1.getPower());
    }

    private Item getTopPowerItem(List<Item> items) {
        Optional<Item> optionalItem = items.stream().findFirst();

        if (!optionalItem.isPresent()) {
            throw new RuntimeException("获取最高项错误");
        }

        return optionalItem.get();
    }

    public Item getTopPowerItem() {
        return getTopPowerItem(inner);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getItems().stream().allMatch(item -> item.getCoefficient().isZero())) {
            return "0";
        }
        getItems().forEach(item -> {
            if (item.getCoefficient().isPositive()) {
                sb.append("+");
            }
            sb.append(item.toString());
        });
        if (sb.indexOf("+") == 0) {
            sb.delete(0, 1);
        }
        return sb.toString();
    }


    public Multinomial sub(Multinomial m1) {
        List<Item> ls1 = getItems();
        List<Item> ls2 = m1.getItems();
        List<Item> result = new ArrayList<>();

        if (ls1.size() > ls2.size()) {
            fillToHigherItem(ls2, getTopPowerItem());
        }else if (ls1.size() < ls2.size()) {
            fillToHigherItem(ls1, m1.getTopPowerItem());
        }

        for (int i = 0; i < ls1.size(); i++) {
            result.add(ls1.get(i).samePowerSub(ls2.get(i)));
        }

        return Multinomial.create(result);
    }

    private void fillToHigherItem(List<Item> ls, Item topPowerItem) {
        int power = topPowerItem.getPower();
        List<Item> filled = new ArrayList<>();
        if (ls.size() == 0) {
            for (int i = 0; i <= power; i++) {
                filled.add(Item.create(0, i));
            }
        } else {
            for (int i = ls.get(0).getPower(); i < topPowerItem.getPower(); i++) {
                filled.add(Item.create(0, i+1));
            }
        }
        ls.addAll(filled);
        sort(ls);
    }

    public void addItem(Item item) {
        inner.add(item);
    }
}
