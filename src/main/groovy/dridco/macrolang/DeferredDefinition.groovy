package dridco.macrolang

class DeferredDefinition implements Definition {

    Set<MacroDefinition> all
    String name

    @Override
    String toString() {
        all.find { it.name == name }
    }
}
