package api.v1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Bz_Repository extends JpaRepository<Member, Long> {
    List<Member> findByCompanyLocationContaining(String keyword);

    // 꿀팁 : 레퍼지토리에서 반환을 Dto로 하게되면 굳이 mapper안쓰고 바로 Controller에서 활용가능
    List<Member> findByCompanyTypeContaining(String keyword);
}
