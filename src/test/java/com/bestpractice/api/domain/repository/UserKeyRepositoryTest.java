package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserKeyRepositoryTest {

    @Mock
    private UserKeyRepository userKeyRepository;

    private final Long correctId = 1L;
    private final String correctRefreshToken = "test_refresh_token";
    private final String correctToken = "test_token";
    private final String correctTokenType = "test_token_type";
    private final Long correctUserId = 2L;
    private final Date correctExpiresAt = new Date();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        UserKey userKey = new UserKey();
        userKey.setId(this.correctId);
        userKey.setRefreshToken(this.correctRefreshToken);
        userKey.setToken(this.correctToken);
        userKey.setTokenType(this.correctTokenType);
        userKey.setUserId(this.correctUserId);
        userKey.setExpiresAt(this.correctExpiresAt);

        when(this.userKeyRepository.findByUserId(this.correctUserId)).thenReturn(userKey);
        assertThat(this.userKeyRepository.findByUserId(this.correctUserId).getToken()).isEqualTo(this.correctToken);
    }

    @Test
    public void testSave() {
        UserKey userKey = new UserKey();
        userKey.setId(this.correctId);
        userKey.setRefreshToken(this.correctRefreshToken);
        userKey.setToken(this.correctToken);
        userKey.setTokenType(this.correctTokenType);
        userKey.setUserId(this.correctUserId);
        userKey.setExpiresAt(this.correctExpiresAt);

        when(this.userKeyRepository.save(userKey)).thenReturn(userKey);
        assertThat(this.userKeyRepository.save(userKey).getToken()).isEqualTo(this.correctToken);
    }
}