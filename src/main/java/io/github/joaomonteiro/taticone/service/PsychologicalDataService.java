package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataRequest;
import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.entity.PsychologicalData;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.repository.PsychologicalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PsychologicalDataService {

    private final PsychologicalDataRepository psychologicalDataRepository;
    private final PlayerRepository playerRepository;

    public PsychologicalDataResponse addPsychologicalData(long playerId, PsychologicalDataRequest dataRequest){
        PlayerProfile playerProfile = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        PsychologicalData psychologicalData = new PsychologicalData();
        psychologicalData.setMotivation(dataRequest.motivation());
        psychologicalData.setConfidence(dataRequest.confidence());
        psychologicalData.setResilience(dataRequest.resilience());
        psychologicalData.setTeamSpirit(dataRequest.teamSpirit());
        psychologicalData.setDiscipline(dataRequest.discipline());
        psychologicalData.setPressureManagement(dataRequest.pressureManagement());
        psychologicalData.setPlayer(playerProfile);

        playerProfile.setPsychologicalData(psychologicalData);
        PsychologicalData saved = psychologicalDataRepository.save(psychologicalData);
        return new PsychologicalDataResponse(saved.getMotivation(),
                saved.getConfidence(),
                saved.getResilience(),
                saved.getTeamSpirit(),
                saved.getDiscipline(),
                saved.getPressureManagement());
    }
}
