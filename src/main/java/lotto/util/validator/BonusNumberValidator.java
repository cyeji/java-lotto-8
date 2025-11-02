package lotto.util.validator;

import java.util.Set;

/**
 * 보너스 번호 유효성 검사기
 */
public class BonusNumberValidator {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private final Set<Integer> winningNumbers;

    public BonusNumberValidator(Set<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    /**
     * 보너스 번호 유효성 검사
     * @param raw
     * @return
     */
    public int validate(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
        try {
            int n = Integer.parseInt(raw.trim());
            if (n < MIN || n > MAX) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
            }
            if (winningNumbers.contains(n)) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되면 안 됩니다.");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}
