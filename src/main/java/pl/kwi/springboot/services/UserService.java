package pl.kwi.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.db.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserEntity> getUserList() {
		return repository.findAll();
	}
	
	public void createUser(UserEntity user) {
		repository.save(user);
	}
	
	public UserEntity readUser(Long id) {
		return repository.findOne(id);
	}
	
	public void updateUser(UserEntity user) {
		repository.save(user);
	}
	
	public void deleteUser(Long id) {
		repository.delete(id);
	}

}
