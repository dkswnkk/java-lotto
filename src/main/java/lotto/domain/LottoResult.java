package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Map<Rank, Long> rankCounts;

    public LottoResult(List<LottoNumber> lottoNumbers, List<LottoAvailableNumber> winningNumbers, LottoAvailableNumber bonusNumber) {
        rankCounts = lottoNumbers.stream()
                .map(lotto -> createRank(lotto, winningNumbers, bonusNumber))
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    public long findRankCount(int matchCount, boolean bonusMatch) {
        Rank rank = Rank.valueOf(matchCount, bonusMatch);

        return rankCounts.getOrDefault(rank, 0L);
    }

    private Rank createRank(LottoNumber lotto, List<LottoAvailableNumber> winningNumbers, LottoAvailableNumber bonusNumber) {
        LottoMatchInfo lottoMatchInfo = LottoMatchInfo.countMatchingNumbers(lotto, winningNumbers, bonusNumber);

        return Rank.valueOf(lottoMatchInfo.getMatchCount(), lottoMatchInfo.isBonusMatch());
    }

}
