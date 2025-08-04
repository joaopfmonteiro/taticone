package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesResponse;
import io.github.joaomonteiro.taticone.entity.DefensiveAttributes;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.DefensiveAttributesRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefensiveAttributesService {

    private final DefensiveAttributesRepository defensiveAttributesRepository;
    private final PlayerRepository playerRepository;

    public DefensiveAttributesResponse addDefensiveAttributes(long playerId, DefensiveAttributesRequest request) {
        PlayerProfile playerProfile = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        DefensiveAttributes defensiveAttributes = new DefensiveAttributes();
        defensiveAttributes.setMarking(request.marking());
        defensiveAttributes.setTackle(request.tackle());
        defensiveAttributes.setDefensivePositioning(request.defensivePositioning());
        defensiveAttributes.setInterceptions(request.interceptions());
        defensiveAttributes.setPressureOnBallCarrier(request.pressureOnBallCarrier());
        defensiveAttributes.setDefensiveCoverage(request.defensiveCoverage());
        defensiveAttributes.setShotBlocking(request.shotBlocking());
        defensiveAttributes.setBallRecovery(request.ballRecovery());
        defensiveAttributes.setDefensiveToOffensiveTransition(request.defensiveToOffensiveTransition());
        defensiveAttributes.setPlayer(playerProfile);

        playerProfile.setDefensiveAttributes(defensiveAttributes);

        DefensiveAttributes saved = defensiveAttributesRepository.save(defensiveAttributes);
        return new DefensiveAttributesResponse(
                saved.getMarking(),
                saved.getTackle(),
                saved.getDefensivePositioning(),
                saved.getInterceptions(),
                saved.getPressureOnBallCarrier(),
                saved.getDefensiveCoverage(),
                saved.getShotBlocking(),
                saved.getBallRecovery(),
                saved.getDefensiveToOffensiveTransition());
    }
}
