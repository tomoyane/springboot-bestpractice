package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoRepositoryTest {

    @Mock
    private InfoRepository infoRepository;

    private final Long correctTestId = 1L;

    @Before
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

        when(this.infoRepository.save(info)).thenReturn(info);
        assertThat(this.infoRepository.save(info).getTitle()).isEqualTo("test");
    }
}