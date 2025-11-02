package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.validator.*;

import java.util.List;
import java.util.Set;

/**
 * 콘솔 입력 처리 클래스
 */
public class ConsoleInput {

    private static final MoneyInput moneyInput = new MoneyInput();
    private final WinningNumbersValidator winningValidator = new WinningNumbersValidator();

    /**
     * 구입 금액 입력
     */
    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        try {
            return moneyInput.validateMoney(Integer.parseInt(input));
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 금액은 숫자여야 합니다.");
        }

    }

    /**
     * 구입 개수 입력
     */
    public int inputCount() {
        String raw = Console.readLine();
        try {
            int count = Integer.parseInt(raw.trim());
            if (count <= 0) {
                throw new IllegalArgumentException("[ERROR] 구매 개수는 양수여야 합니다.");
            }
            System.out.println(count + "개를 구매했습니다.");
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 개수는 숫자여야 합니다.");
        }
    }

    /**
     * 당첨 번호 입력
     */
    public List<Integer> inputNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String numbers = Console.readLine();
        return winningValidator.validate(numbers);
    }

    /**
     * 보너스 번호 입력
     */
    public int inputBonusNumber(Set<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        BonusNumberValidator validator = new BonusNumberValidator(winningNumbers);
        return validator.validate(bonusNumber);
    }
}
