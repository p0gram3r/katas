package codingdojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void test() {
        HelloWorld thingy = new HelloWorld();
        assertThat(thingy.getGreeting(), is("Hello World!"));
    }

}
