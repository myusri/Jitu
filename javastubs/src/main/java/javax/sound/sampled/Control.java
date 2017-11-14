package javax.sound.sampled;

/**
 * @author Damian Minkov
 */
public abstract class Control
{
    private Type type;
    public Type getType() {
        return type;
    }

    public static class Type
    {
        private String name;
        protected Type(String name)
        {
            this.name = name;
        }
    }
}
