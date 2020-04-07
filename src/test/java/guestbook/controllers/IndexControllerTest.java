package guestbook.controllers;

import guestbook.model.GuestbookEntry;
import guestbook.services.GuestbookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @Mock
    private GuestbookService guestbookService;
    @InjectMocks
    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void name() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void should_render_index_page() throws Exception {
        when(guestbookService.findAll()).thenReturn(Collections.singletonList(new GuestbookEntry("User 1", "Awesome")));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
        verify(guestbookService, times(1)).findAll();
    }
}