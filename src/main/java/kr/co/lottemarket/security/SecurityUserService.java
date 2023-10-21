package kr.co.lottemarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.user.UserRepository;

@Service
public class SecurityUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = repo.findById(username).orElseThrow(()->new UsernameNotFoundException(username + "NotFound"));
		UserDetails userDetails = MyUserDetails.builder()
											.user(user)
											.build();
		return userDetails;
	}
	
	
}
