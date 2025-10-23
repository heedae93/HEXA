package spring.hexa.application.provided;

import static org.assertj.core.api.Assertions.assertThat;

//class MemberRegisterManualTest {
//
//    static class MemberRepositoryStub implements MemberRepository {
//
//        @Override
//        public Member save(Member member) {
//            ReflectionTestUtils.setField(member,"id",1L);
//            return member;
//        }
//
//        @Override
//        public Optional<Member> findByEmail(Email email) {
//            return Optional.empty();
//        }
//    }
//
//    static class EmailSenderStub implements EmailSender {
//
//
//        @Override
//        public void send(Email email, String subject, String body) {
//
//        }
//    }
//
//
//    @Test
//    void register () {
//        MemberRegister memberRegister = new MemberService(new MemberRepositoryStub(), new EmailSenderStub() ,MemberFixture.createPasswordEncoder());
//        Member member =  memberRegister.register(MemberFixture.createMemberRegisterRequest());
//
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//    }
//
//
//}