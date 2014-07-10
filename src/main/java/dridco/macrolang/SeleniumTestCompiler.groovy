package dridco.macrolang

import groovy.util.logging.Slf4j

@Slf4j
class SeleniumTestCompiler {

    static String DEFAULT_ENCODING = 'UTF-8'

    Set<MacroDefinition> macros = []
    Set<Definition> definitions = new HashSet<Definition>()

    def SeleniumTestCompiler(Iterable<String> macroSources) {
        macroSources.each {
			def current = parseMacro(it)
			if(macros.find { it.name == current.name }) { throw new IllegalArgumentException("Duplicate macro definition with id ${current.name}") }
			macros << current
		}
    }

    SeleniumTest compile(String source) {
        XmlParser parser = new XmlParser()
        def test = parser.parseText(source)
        def tasks = test.children()
        def name = loadRequired test, 'name'
        log.info "Compiling test case $name"
        def base = loadRequired test, 'base'
        def encoding = test.'@encoding' ?: DEFAULT_ENCODING
        def title = test.'@title' ?: name
        def commands = new StringBuilder()
        tasks.each { Node node ->
            commands.append parse(node)
        }
        new SeleniumTest(name: name, code: """<?xml version="1.0" encoding="$encoding"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=$encoding" />
<link rel="selenium.base" href="$base" />
<title>$title</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">$title</td></tr>
</thead><tbody>$commands
</tbody></table>
</body>
</html>""")
    }

    Task parse(Node source) {
        new Task(definition: define(source), context: source.attributes())
    }

    def define(Node source) {
        def tag = source.name()
        def definition
        switch (tag) {
            case 'command':
                definition = parseCommand source
                break;
            case 'parametrized-macro':
                definition = deferred source.attribute('name').toString()
                break;
            default:
                definition = loadMacro tag.toString()
                break;
        }
        definitions.add definition
        definitions.find { it == definition }
    }

    Definition loadMacro(String name) {
        macros.find { it.name == name } ?: deferred(name)
    }

    DeferredDefinition deferred(String name) {
        new DeferredDefinition(name, macros)
    }

    private MacroDefinition parseMacro(String source) {
        def macro = new XmlParser().parseText(source)
        def name = macro.'@name'
        log.info "Registered macro $name"
        new MacroDefinition(name: name, steps: macro.children().collect { Node node -> parse node })
    }

    private static CommandDefinition parseCommand(Node source) {
        new CommandDefinition(source.'@name', source.'@target', source.'@value')
    }

    private static loadRequired(source, name) {
        def value = source."@$name"
        assert value != null: "Attribute $name is required"
        value
    }
}
