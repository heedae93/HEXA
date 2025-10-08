package spring.hexa.application.required;




import org.springframework.data.repository.Repository;
import spring.hexa.domain.Email;
import spring.hexa.domain.Member;

import java.util.Optional;

public interface MemberRepository extends Repository<Member,Long> {
    Member save (Member member);
    Optional<Member> findByEmail(Email email);
}
