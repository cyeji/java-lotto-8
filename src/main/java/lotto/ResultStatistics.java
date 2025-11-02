package lotto;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultStatistics {
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public ResultStatistics() {
        for (Rank r : Rank.values()) {
            counts.put(r, 0);
        }
    }

    public void add(List<Integer> ticketNumbers, List<Integer> winningNumbers, int bonus) {
        // winningNumbers를 HashSet으로 변환하여 contains 조회를 O(1)로 수행
        Set<Integer> winningSet = new HashSet<>(winningNumbers);
        // ticketNumbers를 Set으로 만들어 보너스 검사에 O(1) 조회 사용
        Set<Integer> ticketSet = new HashSet<>(ticketNumbers);

        int match = 0;
        for (Integer n : ticketNumbers) {
            if (winningSet.contains(n)) {
                match++;
            }
        }
        boolean hasBonus = ticketSet.contains(bonus);
        Rank rank = Rank.of(match, hasBonus);
        if (rank != null) {
            counts.put(rank, counts.get(rank) + 1);
        }
    }

    public int getCount(Rank rank) {
        return counts.getOrDefault(rank, 0);
    }

    public long totalPrize() {
        long sum = 0L;
        for (Rank r : Rank.values()) {
            sum += (long) counts.get(r) * r.prize();
        }
        return sum;
    }

    public void printStatistics(long purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---");
        // order: FIFTH(3), FOURTH(4), THIRD(5), SECOND(5+bonus), FIRST(6)
        System.out.println(Rank.FIFTH.formatMessage(getCount(Rank.FIFTH)));
        System.out.println(Rank.FOURTH.formatMessage(getCount(Rank.FOURTH)));
        System.out.println(Rank.THIRD.formatMessage(getCount(Rank.THIRD)));
        System.out.println(Rank.SECOND.formatMessage(getCount(Rank.SECOND)));
        System.out.println(Rank.FIRST.formatMessage(getCount(Rank.FIRST)));

        double rate = 0.0;
        if (purchaseMoney > 0) {
            rate = (double) totalPrize() / purchaseMoney * 100.0;
            rate = Math.round(rate * 10.0) / 10.0; // 소수점 둘째 자리에서 반올림
        }
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
