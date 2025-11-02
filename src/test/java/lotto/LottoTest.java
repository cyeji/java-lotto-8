package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @ParameterizedTest
    @MethodSource("invalidSizeProvider")
    @DisplayName("로또 번호의 개수가 6이 아니면 예외가 발생한다 (파라미터)")
    void 로또_번호_개수_검사(List<Integer> input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<List<Integer>> invalidSizeProvider() {
        return Stream.of(
                List.of(),
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외 발생 (파라미터)")
    void 로또_번호_범위_검사(int invalidNumber) {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, invalidNumber)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 45 사이");
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    void 로또_번호_중복_검사() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("방어적 복사·정렬·불변성 확인")
    void 방어적_복사_정렬_불변성() {
        List<Integer> input = new ArrayList<>(List.of(6, 2, 5, 1, 4, 3));
        Lotto lotto = new Lotto(input);

        // 정렬되어 반환되어야 함
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);

        // 원본 리스트 수정해도 Lotto 내부는 변하지 않음
        input.set(0, 99);
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);

        // 반환 리스트는 불변이어야 함
        assertThatThrownBy(() -> lotto.getNumbers().add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("countMatching, contains, equals, hashCode 동작 확인")
    void 동작_유틸_메서드_확인() {
        Lotto a = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto b = new Lotto(List.of(4, 5, 6, 7, 8, 9));

        assertThat(a.countMatching(b)).isEqualTo(3);
        assertThat(a.contains(3)).isTrue();
        assertThat(a.contains(10)).isFalse();

        Lotto aCopy = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(a).isEqualTo(aCopy);
        assertThat(a.hashCode()).isEqualTo(aCopy.hashCode());
    }
}
