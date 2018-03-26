package com.bestpractice.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.bestpractice.api.domain.model.CredentialModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
    public void testDecodeJwt_Ok() {
        String jwt = jsonWebTokenService.generateJwt(userId, period, userUuid);

        CredentialModel credentialModel = jsonWebTokenService.decodeJwt(jwt);

        assertThat(credentialModel.getSub()).isEqualTo(userId);
        assertThat(credentialModel.getJti()).isEqualTo(userUuid);
    }

    @Test
    public void testDecodeJwt_Unauthorized() {
        String jwt = jsonWebTokenService.generateJwt(userId, period, userUuid);
        String invalidJwt = jwt + "NG";

        CredentialModel credentialModel = jsonWebTokenService.decodeJwt(invalidJwt);

        assertThat(credentialModel.getSub()).isNull();
        assertThat(credentialModel.getExp()).isNull();
        assertThat(credentialModel.getJti()).isNull();
    }
}