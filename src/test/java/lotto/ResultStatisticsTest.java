package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ResultStatisticsTest {

    @Test
    void 여러_등수에_대해_카운트와_총상금이_올바르다() {
        ResultStatistics stats = new ResultStatistics();

        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;

        // FIFTH (3개 일치)
        stats.add(List.of(1, 2, 3, 8, 9, 10), winning, bonus);
        // FOURTH (4개 일치)
        stats.add(List.of(1, 2, 3, 4, 9, 10), winning, bonus);
        // THIRD (5개 일치)
        stats.add(List.of(1, 2, 3, 4, 5, 11), winning, bonus);
        // SECOND (5개 + 보너스)
        stats.add(List.of(1, 2, 3, 4, 5, 7), winning, bonus);
        // FIRST (6개)
        stats.add(List.of(1, 2, 3, 4, 5, 6), winning, bonus);

        assertThat(stats.getCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(stats.getCount(Rank.FOURTH)).isEqualTo(1);
        assertThat(stats.getCount(Rank.THIRD)).isEqualTo(1);
        assertThat(stats.getCount(Rank.SECOND)).isEqualTo(1);
        assertThat(stats.getCount(Rank.FIRST)).isEqualTo(1);

        long expectedTotal = Rank.FIFTH.prize()
                + Rank.FOURTH.prize()
                + Rank.THIRD.prize()
                + Rank.SECOND.prize()
                + Rank.FIRST.prize();

        assertThat(stats.totalPrize()).isEqualTo(expectedTotal);
    }

    @Test
    void 당첨자가_없을_때_총상금은_0이다() {
        ResultStatistics stats = new ResultStatistics();
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;

        stats.add(List.of(10, 11, 12, 13, 14, 15), winning, bonus);
        assertThat(stats.totalPrize()).isEqualTo(0L);
    }
}
