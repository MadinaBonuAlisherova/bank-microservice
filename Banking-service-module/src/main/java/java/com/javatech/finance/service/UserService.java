package java.com.javatech.finance.service;

import com.javatech.finance.exceptions.EntityNotFoundException;
import com.javatech.finance.model.dto.User;
import com.javatech.finance.model.entity.UserEntity;
import com.javatech.finance.model.mapper.UserMapper;
import com.javatech.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserMapper userMapper = new UserMapper();

    private final UserRepository userRepository;

    public User readUser(String identification) {
        UserEntity userEntity = userRepository.findByIdentificationNumber(identification).orElseThrow(EntityNotFoundException::new);
        return userMapper.convertToDto(userEntity);
    }

    public List<User> readUsers(Pageable pageable) {
        return userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());
    }
}
