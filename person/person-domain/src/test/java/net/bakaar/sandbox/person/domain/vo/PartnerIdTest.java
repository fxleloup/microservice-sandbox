package net.bakaar.sandbox.person.domain.vo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class PartnerIdTest {

    @Test
    public void of_should_set_the_field() {
        //Given
        long id = 12345;
        //When
        PartnerId created = PartnerId.of(id);
        //Then
        assertThat(created).isNotNull();
        assertThat(getField(created,"number")).isEqualTo(id);
    }
}