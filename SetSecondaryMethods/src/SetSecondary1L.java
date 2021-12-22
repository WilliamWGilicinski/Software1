import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> removeElements = this.newInstance();
        Set<T> copy = this.newInstance();
        copy.transferFrom(this);

        int last = copy.size();
        for (int i = 0; i < last; i++) {
            T elements = copy.removeAny();
            if (!s.contains(elements)) {
                this.add(elements);
            } else {
                removeElements.add(elements);
            }
        }
        return removeElements;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> copy = s.newInstance();
        copy.transferFrom(s);

        int last = copy.size();
        for (int i = 0; i < last; i++) {
            T element = copy.removeAny();
            if (!this.contains(element)) {
                this.add(element);
            } else {
                s.add(element);
            }
        }

    }

}