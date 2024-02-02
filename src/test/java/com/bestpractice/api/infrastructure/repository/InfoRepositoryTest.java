package com.bestpractice.api.infrastructure.repository;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class InfoRepositoryTest {

    @Mock
    private InfoPersistentRepository infoRepository;

    private final Long correctTestId = 1L;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {

        Info info = new Info();
        info.setId(this.correctTestId);
        info.setTitle("test");
        info.setDescription("test");

        when(this.infoRepository.findAll()).thenReturn(Collections.singletonList(info));
        assertThat(this.infoRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void testFindById() {
        Info info = new Info();
        info.setId(this.correctTestId);
        info.setTitle("test");
        info.setDescription("test");

        when(this.infoRepository.findById(this.correctTestId)).thenReturn(info);
        assertThat(this.infoRepository.findById(this.correctTestId).getTitle()).isEqualTo("test");
    }

    @Test
    public void testSave() {
        Info info = new Info();
        info.setId(this.correctTestId);
        info.setTitle("test");
        info.setDescription("test");

        when(this.infoRepository.insert(info)).thenReturn(info);
        assertThat(this.infoRepository.insert(info).getTitle()).isEqualTo("test");
    }
}