package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesResponse;
import io.github.joaomonteiro.taticone.entity.OffensiveAttributes;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.OffensiveAttributesRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OffensiveAttributesService{

    private final OffensiveAttributesRepository offensiveAttributesRepository;
    private final PlayerRepository playerRepository;

    public OffensiveAttributesResponse addOffensiveAttributes(long playerId, OffensiveAttributesRequest request){
        PlayerProfile playerProfile = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        OffensiveAttributes offensiveAttributes = new OffensiveAttributes();
        offensiveAttributes.setStickHandling(request.stickHandling());
        offensiveAttributes.setPassing(request.passing());
        offensiveAttributes.setShooting(request.shooting());
        offensiveAttributes.setSpeedWithBall(request.speedWithBall());
        offensiveAttributes.setGameVision(request.gameVision());
        offensiveAttributes.setOffTheBallMovement(request.offTheBallMovement());
        offensiveAttributes.setPlaymakingAbility(request.playmakingAbility());
        offensiveAttributes.setFinishing(request.finishing());
        offensiveAttributes.setOffensiveToDefensiveTransition(request.offensiveToDefensiveTransition());
        offensiveAttributes.setPlayer(playerProfile);

        playerProfile.setOffensiveAttributes(offensiveAttributes);

        OffensiveAttributes saved = offensiveAttributesRepository.save(offensiveAttributes);
        return new OffensiveAttributesResponse(
                saved.getStickHandling(),
                saved.getPassing(),
                saved.getShooting(),
                saved.getSpeedWithBall(),
                saved.getGameVision(),
                saved.getOffTheBallMovement(),
                saved.getPlaymakingAbility(),
                saved.getFinishing(),
                saved.getOffensiveToDefensiveTransition()
        );
    }
}
