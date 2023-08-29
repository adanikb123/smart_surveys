package epolsoft.practice.smart_surveys.services;

import epolsoft.practice.smart_surveys.dto.UserVoteResponseDto;
import epolsoft.practice.smart_surveys.entity.UserVote;

import java.util.List;

public interface UserVoteService {
    List<UserVoteResponseDto> createUserVotes(List<UserVoteResponseDto> userVotes);

    List<UserVote> getAllVotes();
}
