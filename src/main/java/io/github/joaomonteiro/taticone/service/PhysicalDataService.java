package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.PhysicalDataRequest;
import io.github.joaomonteiro.taticone.dto.player.PhysicalDataResponse;
import io.github.joaomonteiro.taticone.entity.PhysicalData;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.PhysicalDataRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhysicalDataService {

    private final PhysicalDataRepository physicalDataRepository;
    private final PlayerRepository playerRepository;

    public PhysicalDataResponse addPhysicalData(long playerId, PhysicalDataRequest physicalDataRequest){
        PlayerProfile playerProfile = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        PhysicalData physicalData = new PhysicalData();
        physicalData.setWeight(physicalDataRequest.weight());
        physicalData.setHeight(physicalDataRequest.height());
        physicalData.setStrength(physicalDataRequest.strength());
        physicalData.setSpeed(physicalDataRequest.speed());
        physicalData.setEndurance(physicalDataRequest.endurance());
        physicalData.setAgility(physicalDataRequest.agility());

        physicalData.setPlayer(playerProfile);
        playerProfile.setPhysicalData(physicalData);

        PhysicalData saved = physicalDataRepository.save(physicalData);
        return new PhysicalDataResponse(
                saved.getWeight(),
                saved.getHeight(),
                saved.getStrength(),
                saved.getSpeed(),
                saved.getEndurance(),
                saved.getAgility());
    }


}
