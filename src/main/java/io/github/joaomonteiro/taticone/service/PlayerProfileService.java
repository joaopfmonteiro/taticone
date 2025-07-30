package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PlayerResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PlayerResponse> getPlayersSortBy(String sort, String direction) {
        if(!sort.equals("age") && !sort.equals("name")){
            throw new IllegalArgumentException("Invalid sort: " + sort);
        }

        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        return playerRepository.findAll(Sort.by(dir,sort))
                .stream()
                .map(playerProfile -> new PlayerResponse(
                        playerProfile.getId(),
                        playerProfile.getName(),
                        playerProfile.getAge(),
                        playerProfile.getNationality()
                ))
                .toList();
    }
}
