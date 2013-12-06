package dridco.macrolang

import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class DeferredDefinition implements Definition {

    String name
    private Set<MacroDefinition> all
    private Template template

    def DeferredDefinition(String name, Set<MacroDefinition> all) {
        this.all = all
        this.name = name

        template = new SimpleTemplateEngine().createTemplate("$name")
    }

    String render(Map<String, String> context) {
        def name = template.make(context).toString()
        def found = all.find { it.name == name }
        if (found) {
            found.render context
        } else {
            throw new IllegalArgumentException("Requested inexisting macro $name")
        }
    }
}
