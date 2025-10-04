package spring.hexa.application.required;




import org.springframework.data.repository.Repository;
import spring.hexa.domain.Member;

public interface MemberRepository extends Repository<Member,Long> {
    Member save (Member member);
}
