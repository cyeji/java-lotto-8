package lotto;

public enum Rank {
    FIRST(6, false, 2_000_000_000L, "6개 일치 (2,000,000,000원) - %d개"),
    SECOND(5, true, 30_000_000L, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개"),
    THIRD(5, false, 1_500_000L, "5개 일치 (1,500,000원) - %d개"),
    FOURTH(4, false, 50_000L, "4개 일치 (50,000원) - %d개"),
    FIFTH(3, false, 5_000L, "3개 일치 (5,000원) - %d개");

    private final int matchCount;
    private final boolean requireBonus;
    private final long prize;
    private final String messageFormat;

    Rank(int matchCount, boolean requireBonus, long prize, String messageFormat) {
        this.matchCount = matchCount;
        this.requireBonus = requireBonus;
        this.prize = prize;
        this.messageFormat = messageFormat;
    }

    public int matchCount() {
        return matchCount;
    }

    public boolean requireBonus() {
        return requireBonus;
    }

    public long prize() {
        return prize;
    }

    public String formatMessage(int count) {
        return String.format(messageFormat, count);
    }

    public static Rank of(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return null;
    }
}

