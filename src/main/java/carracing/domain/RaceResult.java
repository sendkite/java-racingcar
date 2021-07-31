package carracing.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RaceResult {

    public static final String NON_NULL_ROUND_RECORDS = "roundRecords 리스트 입력값은 null 일 수 없습니다";
    public static final String NOT_EMPTY_ROUND_RECORDS = "roundRecords 리스트 입력값은 비어있을 수 없습니다";

    private final List<RoundRecord> roundRecords;
    private final List<String> winners;

    public RaceResult(List<RoundRecord> roundRecords) {
        validateRounds(roundRecords);
        this.roundRecords = roundRecords;
        this.winners = extractWinners(roundRecords);
    }

    private void validateRounds(List<RoundRecord> roundRecords) {
        requireNonNull(roundRecords);
        requireNotEmpty(roundRecords);
    }

    private void requireNonNull(List<RoundRecord> roundRecords) {
        if (Objects.isNull(roundRecords)) {
            throw new IllegalArgumentException(NON_NULL_ROUND_RECORDS);
        }
    }

    private void requireNotEmpty(List<RoundRecord> roundRecords) {
        if (roundRecords.isEmpty()) {
            throw new IllegalArgumentException(NOT_EMPTY_ROUND_RECORDS);
        }
    }

    private List<String> extractWinners(List<RoundRecord> roundRecords) {
        return getLastRound(roundRecords).getBestCarNames();
    }

    private RoundRecord getLastRound(List<RoundRecord> roundRecords) {
        return roundRecords.get(roundRecords.size() - 1);
    }

    public List<RoundRecord> getRoundRecords() {
        return Collections.unmodifiableList(roundRecords);
    }

    public List<String> getWinners() {
        return Collections.unmodifiableList(winners);
    }
}
