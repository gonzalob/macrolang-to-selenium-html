package dridco.macrolang

import groovy.text.SimpleTemplateEngine
import groovy.transform.Immutable

@Immutable
class Task {

    Definition definition
    Map<String, String> context

    @Override
    String toString() {
        def template = new SimpleTemplateEngine().createTemplate(definition.toString())
        template.make(new HashMap(context)).toString()
    }
}
