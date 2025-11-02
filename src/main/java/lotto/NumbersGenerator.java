package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 무작위로 1~45 범위의 중복 없는 6개 숫자를 생성하여 오름차순으로 반환하는 유틸
 */
public class NumbersGenerator {

    public static List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(numbers);
        return numbers;
    }
}
