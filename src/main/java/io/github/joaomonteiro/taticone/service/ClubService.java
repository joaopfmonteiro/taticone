package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.club.ClubRequest;
import io.github.joaomonteiro.taticone.dto.club.ClubResponse;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubResponse createClub(ClubRequest clubRequest){

        Club club = new Club();
        club.setName(clubRequest.name());
        Club saved = clubRepository.save(club);

        return new ClubResponse(saved.getId(), saved.getName());
    }
}
