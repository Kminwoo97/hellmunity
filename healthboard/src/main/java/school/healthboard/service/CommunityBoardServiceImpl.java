package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.repository.CommunityBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityBoardServiceImpl implements CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;

    @Override
    public CommunityBoard save(CommunityBoard communityBoard) {
        return communityBoardRepository.save(communityBoard);
    }

    @Override
    public List<CommunityBoard> findAll() {
        return communityBoardRepository.findAll();
    }
}
