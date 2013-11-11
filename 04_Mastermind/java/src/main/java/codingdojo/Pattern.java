package codingdojo;

import java.util.Arrays;
import java.util.List;

public class Pattern {

    private List<Color> colors;

    public Pattern(List<Color> colors) {
        if (colors == null) {
            throw new NullPointerException("color list cannot be null!");
        }

        this.colors = colors;
    }

    public Pattern(Color... colors) {
        this.colors = Arrays.asList(colors);
    }

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + colors.hashCode();
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
        Pattern other = (Pattern) obj;
        if (!colors.equals(other.colors))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Color color : colors) {
            sb.append(color.toString()).append("-");
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }
}
