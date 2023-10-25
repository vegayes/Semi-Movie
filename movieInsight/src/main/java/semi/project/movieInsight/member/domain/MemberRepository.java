package semi.project.movieInsight.member.domain;

import org.apache.ibatis.annotations.Mapper;

import semi.project.movieInsight.member.dto.Member;

@Mapper
public interface MemberRepository {
	 // JOIN
    void register(Member member) throws Exception;
    int idCheck(String id_check) throws Exception;

}
