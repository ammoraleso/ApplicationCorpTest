package co.com.applicationcorp.repository;

import co.com.applicationcorp.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByLibrariesId(Long libraryId, Pageable pageable);
}