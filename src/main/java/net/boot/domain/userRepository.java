package net.boot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
//extends JpaRepository<어떤클래스인지?, id의 타입>
public interface userRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

}
