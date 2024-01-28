package com.bestpractice.api.infrastructure.repository;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private final Long correctId = 1L;
    private final String correctUuid = UUID.randomUUID().toString();
    private final String correctUserName = "test";
    private final String correctEmail = "test@gmail.com";
    private final String correctPassword = "test1234";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setId(this.correctId);
        user.setUuid(this.correctUuid);
        user.setEmail(this.correctEmail);
        user.setUsername(this.correctUserName);
        user.setPassword(this.correctPassword);

        when(this.userRepository.findByEmail(this.correctEmail)).thenReturn(user);
        assertThat(this.userRepository.findByEmail(this.correctEmail).getEmail()).isEqualTo(this.correctEmail);
    }

    @Test
    public void testFindByIdAndUuid() {
        User user = new User();
        user.setId(this.correctId);
        user.setUuid(this.correctUuid);
        user.setEmail(this.correctEmail);
        user.setUsername(this.correctUserName);
        user.setPassword(this.correctPassword);

        when(this.userRepository.findByIdAndUuid(this.correctId, this.correctEmail)).thenReturn(user);
        assertThat(this.userRepository.findByIdAndUuid(
                this.correctId, this.correctEmail).getEmail()).isEqualTo(this.correctEmail);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setId(this.correctId);
        user.setUuid(this.correctUuid);
        user.setEmail(this.correctEmail);
        user.setUsername(this.correctUserName);
        user.setPassword(this.correctPassword);

        when(this.userRepository.save(user)).thenReturn(user);
        assertThat(this.userRepository.save(user).getEmail()).isEqualTo(this.correctEmail);
    }

    @Test
    public void testRemoveById() {
        User user = new User();
        user.setId(this.correctId);
        user.setUuid(this.correctUuid);
        user.setEmail(this.correctEmail);
        user.setUsername(this.correctUserName);
        user.setPassword(this.correctPassword);

        when(this.userRepository.removeById(this.correctId)).thenReturn(user);
        assertThat(this.userRepository.removeById(this.correctId).getEmail()).isEqualTo(this.correctEmail);
    }
}