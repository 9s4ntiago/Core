package de.darkmc.coreutils.collection;

import java.util.HashSet;

public class NautSet<E> extends HashSet<E>
{
    public boolean notContains(Object o)
    {
        return !contains(o);
    }
}
