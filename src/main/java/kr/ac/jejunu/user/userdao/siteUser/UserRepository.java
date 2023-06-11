package kr.ac.jejunu.user.userdao.siteUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
   Optional<SiteUser> findByusername(String username);

   SiteUser getByUsername(String username);
}