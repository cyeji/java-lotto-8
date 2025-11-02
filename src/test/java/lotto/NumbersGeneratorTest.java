package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class NumbersGeneratorTest {

    @Test
    void 여섯개의_중복_없고_정렬된_숫자를_생성한다() {
        List<Integer> nums = NumbersGenerator.generate();

        assertThat(nums).hasSize(6);
        assertThat(nums).allMatch(n -> n >= 1 && n <= 45);
        assertThat(nums).doesNotHaveDuplicates();
        assertThat(nums).isSorted();
    }
}
