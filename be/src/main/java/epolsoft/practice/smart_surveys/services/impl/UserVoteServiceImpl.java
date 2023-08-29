package epolsoft.practice.smart_surveys.services.impl;

import epolsoft.practice.smart_surveys.dto.UserVoteResponseDto;
import epolsoft.practice.smart_surveys.entity.User;
import epolsoft.practice.smart_surveys.entity.UserVote;
import epolsoft.practice.smart_surveys.mapper.UserVoteMapper;
import epolsoft.practice.smart_surveys.repository.UserVoteRepository;
import epolsoft.practice.smart_surveys.services.AnswerOptionService;
import epolsoft.practice.smart_surveys.services.UserService;
import epolsoft.practice.smart_surveys.services.UserVoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserVoteServiceImpl implements UserVoteService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserVoteRepository userVoteRepository;
    @Autowired
    private UserVoteMapper userVoteMapper;
    @Autowired
    private AnswerOptionService answerOptionService;

    @Override
    public List<UserVoteResponseDto> createUserVotes(List<UserVoteResponseDto> userVotes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((User)authentication.getPrincipal()).getId();
        for (UserVoteResponseDto userVoteResponseDto: userVotes) {
            userVoteResponseDto.setUserId(userId);
        }

        answerOptionService.checkAll(userVotes);
        this.userVoteRepository.saveAll(userVoteMapper.toEntity(userVotes));
        return userVotes;
    }

    @Override
    public List<UserVote> getAllVotes(){
        return userVoteRepository.findAll();
    }
}
