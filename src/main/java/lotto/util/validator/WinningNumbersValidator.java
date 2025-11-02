package lotto.util.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 당첨 번호 유효성 검사기
 */
public class WinningNumbersValidator {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    /**
     * 당첨 번호 유효성 검사
     * @param raw 쉼표로 구분된 6개의 숫자(문자열) 입력값
     * @return 유효성 검사를 통과한, 오름차순으로 정렬된 당첨 번호의 정수 리스트
     */
    public List<Integer> validate(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 6개 숫자여야 합니다.");
        }

        String[] parts = raw.split(",");
        if (parts.length != SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 6개 숫자여야 합니다.");
        }

        List<Integer> nums = new ArrayList<>();
        Arrays.stream(parts).forEach(p -> {
            try {
                int n = Integer.parseInt(p.trim());
                if (n < MIN || n > MAX) {
                    throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                }
                nums.add(n);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }
        });

        Set<Integer> set = new HashSet<>(nums);
        if (set.size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복이 있으면 안 됩니다.");
        }

        Collections.sort(nums);
        return nums;
    }
}

