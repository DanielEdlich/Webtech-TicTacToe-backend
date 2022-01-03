package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.persistence.GameRepository;
import htw.berlin.webtech.ticktacktoe.persistence.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameService underTest;


    @Test
    @DisplayName("should return true if delete was successful")
    void userToDeleteExists() {
        // given
        Long givenId = 50L;
        doReturn(true).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteGame(givenId);

        // then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("should return false if user to delete does not exist")
    void userToDeleteNotExists() {
        // given
        Long givenId = 50L;
        doReturn(false).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteGame(givenId);

        // then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }
}
