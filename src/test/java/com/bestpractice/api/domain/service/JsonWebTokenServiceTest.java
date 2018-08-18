package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.bestpractice.api.domain.model.Credential;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonWebTokenServiceTest {

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    private static final Long userId = 1L;
    private static final int period = 1000;
    private static final String userUuid = UUID.randomUUID().toString();

    @Test
    public void testGenerateJwt() {
        assertThat(jsonWebTokenService.generateJwt(
                userId,
                period,
                userUuid)
        ).isNotEmpty();
    }

    @Test
    public void testVerifyJwt_Ok() {
        String jwt = jsonWebTokenService.generateJwt(userId, period, userUuid);
        String invalidJwt = jwt + "NG";

        assertThat(jsonWebTokenService.verifyJwt(invalidJwt)).isFalse();
    }

    @Test
    public void testVerifyJwt_Unauthorized() {
        String jwt = jsonWebTokenService.generateJwt(userId, period, userUuid);
        assertThat(jsonWebTokenService.verifyJwt(jwt)).isTrue();
    }

    @Test
    public void testDecodeJwt_Ok() {
        String jwt = jsonWebTokenService.generateJwt(userId, period, userUuid);

        Credential credential = jsonWebTokenService.decodeJwt(jwt);

        assertThat(credential.getSub()).isEqualTo(userId);
        assertThat(credential.getJti()).isEqualTo(userUuid);
    }
}