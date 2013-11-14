package dridco.macrolang

class DeferredDefinition implements Definition {

    Set<MacroDefinition> all
    String name

    @Override
    String toString() {
        def found = all.find { it.name == name }
        if (found) found
        else throw new IllegalArgumentException("Requested inexisting macro $name")
    }
}
