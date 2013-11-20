package dridco.macrolang

import groovy.text.SimpleTemplateEngine

class DeferredDefinition {

    Set<MacroDefinition> all
    String name

    String render(Map<String, String> context) {
        def name = new SimpleTemplateEngine().createTemplate("$name").make(context).toString()
        def found = all.find { it.name == name }
        if (found) found.render context
        else throw new IllegalArgumentException("Requested inexisting macro $name")
    }
}
