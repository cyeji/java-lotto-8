package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    void 요청한_수량만큼_티켓을_발행한다() {
        LottoMachine machine = new LottoMachine();
        List<Lotto> tickets = machine.issueByCount(3);

        assertThat(tickets).hasSize(3);
        tickets.forEach(t -> assertThat(t.getNumbers()).hasSize(6));
    }

    @Test
    void 금액기준으로_올바른_수량을_발행한다() {
        LottoMachine machine = new LottoMachine();
        List<Lotto> tickets = machine.issueByMoney(5000);

        assertThat(tickets).hasSize(5);
    }

    @Test
    void 금액이_유효하지_않으면_예외를_던진다() {
        LottoMachine machine = new LottoMachine();
        assertThatThrownBy(() -> machine.issueByMoney(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수가_유효하지_않으면_예외를_던진다() {
        LottoMachine machine = new LottoMachine();
        assertThatThrownBy(() -> machine.issueByCount(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
