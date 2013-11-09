package codingdojo;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Combination {

    private List<Color> colors;

    public Combination(List<Color> colors) {
        this.colors = colors;
    }

    public Combination(Color... colors) {
        this.colors = Arrays.asList(colors);
    }

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((colors == null) ? 0 : colors.hashCode());
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
        if (colors == null) {
            if (other.colors != null)
                return false;
        } else if (!colors.equals(other.colors))
            return false;
        return true;
    }

}
