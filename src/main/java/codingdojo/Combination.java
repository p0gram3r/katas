package codingdojo;

import java.awt.Color;
import java.util.Arrays;

public class Combination {

    private Color[] colors;

    public Combination(Color... colors) {
        this.colors = colors;
    }

    public Color[] getColors() {
        return colors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(colors);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Combination other = (Combination) obj;
        if (!Arrays.equals(colors, other.colors))
            return false;
        return true;
    }

}
