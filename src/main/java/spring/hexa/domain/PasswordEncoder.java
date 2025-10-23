package spring.hexa.domain;


/**
 * required 패키지 아래 생성하는게 맞는 것처럼 보이지만 그렇게 되면 domain 레이어에서 PasswordEncoder를
 * 가지고 올 때 의존 방향이 반대가 되기 때문에 domain 패키지 아래 위치시키는게 맞다.
 * 어차피 domain 레이어는 application 레이어 안쪽에 위치하기 때문에 application 레이어에 속해 있다고도
 * 볼 수 있기 때문이다.
 */
public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
