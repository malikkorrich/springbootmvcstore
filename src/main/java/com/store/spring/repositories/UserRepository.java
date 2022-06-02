package com.store.spring.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.spring.models.Usuario;

@Repository
public interface UserRepository  extends JpaRepository<Usuario, Long>{

		public Usuario findFirstByEmail(String email);
		
		@Transactional
		@Modifying
		@Query("update Usuario u set u.name=?1, u.email=?2, u.password=?3, u.avatar=?4 where u.id=?5") int update(String name,String email, String password, String avatar ,long id);
}
