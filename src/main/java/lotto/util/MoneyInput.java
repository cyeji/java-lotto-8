package lotto.util;

public class MoneyInput {

    /**
     * 금액 유효성 검사
     * @param money
     */
    public int validateMoney(int money) {
        if (money < 1000 || money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위로 입력해야 합니다.");
        }

        return money;
    }
}
