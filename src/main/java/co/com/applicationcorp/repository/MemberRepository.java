package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}