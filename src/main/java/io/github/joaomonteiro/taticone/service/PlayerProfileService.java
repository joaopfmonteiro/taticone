package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PlayerResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerProfileService {

    private final PlayerRepository playerRepository;

    public PlayerResponse createPlayer(CreatePlayerRequest playerDto){
        PlayerProfile player = PlayerProfile.builder()
                .name(playerDto.name())
                .age(playerDto.age())
                .nationality(playerDto.nationality())
                .linkedUser(null)
                .build();
        PlayerProfile saved = playerRepository.save(player);
        return new PlayerResponse(saved.getId(), saved.getName(), saved.getAge(), saved.getNationality());
    }

}
