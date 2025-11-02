package lotto;

import lotto.util.ConsoleInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    private static final ConsoleInput consoleInput = new ConsoleInput();


    public static void main(String[] args) {
        int money = consoleInput.inputMoney();
        int count = consoleInput.inputCount();
        List<Integer> winningNumbers = consoleInput.inputNumbers();
        Set<Integer> winningSet = new HashSet<>(winningNumbers);
        int bonus = consoleInput.inputBonusNumber(winningSet);

    }
}
