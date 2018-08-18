package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private final Long correctId = 1L;
    private final String correctUuid = UUID.randomUUID().toString();
    private final String correctUserName = "test";
    private final String correctEmail = "test@gmail.com";
    private final String correctPassword = "test1234";

    @Before
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