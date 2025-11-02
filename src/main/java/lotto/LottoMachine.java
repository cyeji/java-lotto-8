package lotto;

import java.util.ArrayList;
import java.util.List;

/**
 * 로또 발행기
 */
public class LottoMachine {
    private static final int UNIT = 1000;

    public List<Lotto> issueByCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("[ERROR] 발행 개수는 양수여야 합니다.");
        }
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Lotto(NumbersGenerator.generate()));
        }
        return result;
    }

    public List<Lotto> issueByMoney(int money) {
        if (money <= 0 || money % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위의 양수여야 합니다.");
        }
        int count = money / UNIT;
        return issueByCount(count);
    }
}

