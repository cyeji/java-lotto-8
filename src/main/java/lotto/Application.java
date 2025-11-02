package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.ConsoleInput;

public class Application {

    private static final ConsoleInput consoleInput = new ConsoleInput();


    public static void main(String[] args) {
        try {
            int money = consoleInput.inputMoney();
            int count = money / 1000;

            LottoMachine machine = new LottoMachine();
            List<Lotto> tickets = machine.issueByCount(count);

            System.out.println(count + "개를 구매했습니다.");
            for (Lotto t : tickets) {
                System.out.println(t);
            }

            List<Integer> winningNumbers = consoleInput.inputNumbers();
            Set<Integer> winningSet = new HashSet<>(winningNumbers);
            int bonus = consoleInput.inputBonusNumber(winningSet);

            ResultStatistics stats = new ResultStatistics();
            for (Lotto t : tickets) {
                stats.add(t.getNumbers(), winningNumbers, bonus);
            }

            stats.printStatistics(money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
