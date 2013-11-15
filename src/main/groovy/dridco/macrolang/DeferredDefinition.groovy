package dridco.macrolang

class DeferredDefinition {

    Set<MacroDefinition> all
    String name

    String render(Map<String, String> context) {
        def found = all.find { it.name == name }
        if (found) found.render context
        else throw new IllegalArgumentException("Requested inexisting macro $name")
    }
}
