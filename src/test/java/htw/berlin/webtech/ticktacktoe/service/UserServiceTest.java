package htw.berlin.webtech.ticktacktoe.service;


import htw.berlin.webtech.ticktacktoe.persistence.UserEntity;
import htw.berlin.webtech.ticktacktoe.persistence.UserRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements WithAssertions {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService underTest;

    private UserService underTest2 = new UserService(repository);

    @Test
    @DisplayName("should return true if delete was successful")
    void userToDeleteExists() {
        // given
        Long givenId = 111L;
        doReturn(true).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }
    @Test
    @DisplayName("should return false if user to delete does not exist")
    void userToDeleteNotExists() {
        // given
        Long givenId = 111L;
        doReturn(false).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("should transform PersonEntity to Person")
    void should_transform_person_entity_to_person() {
        // given
        var userEntity = Mockito.mock(UserEntity.class);
        doReturn(111L).when(userEntity).getId();
        doReturn("Django").when(userEntity).getName();
        doReturn(5).when(userEntity).getHighscore();
        doReturn("abcde").when(userEntity).getPassword();


        // when
        var result = underTest.transformUserEntityPublic(userEntity);

        // then
        assertThat(result.getId()).isEqualTo(111L);
        assertThat(result.getName()).isEqualTo("Django");
        assertThat(result.getHighscore()).isEqualTo(5);
        assertThat(result.getPassword()).isEqualTo("abcde");
    }


    @Autowired
    private MockMvc mock;

    @Autowired
    private  UserService service;

//    @Autowired
//    PasswordEncoder encoder;

    @Test
    @Disabled
    void comparePassword() {
        String password = "sCds)#5X6c'.";

        String encodedPassword = "" ;

        assertEquals("Passwords not equal",true, service.comparePassword(encodedPassword, password));

    }


}
