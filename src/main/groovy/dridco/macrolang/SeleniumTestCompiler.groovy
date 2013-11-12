package dridco.macrolang

class SeleniumTestCompiler {

    def macros = [] as Set

    def SeleniumTestCompiler(Iterable<String> macroSources) {
        macroSources.each { macros << parseMacro(it) }
    }

    def compile(String source) {
        XmlParser parser = new XmlParser()
        def test = parser.parseText source
        def tasks = test.children()
        def encoding = test.'@encoding'
        def base = test.'@base'
        def title = test.'@title'
        def commands = new StringBuilder()
        tasks.each {
            commands.append parse(it)
        }
        """<?xml version="1.0" encoding="UTF-8"?>
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
</html>"""
    }

    private parse(source) {
        if (source.name() == 'command') {
            parseCommand(source)
        } else {
            macros.find { it.name == source.name() }
        }
    }

    private parseMacro(String source) {
        XmlParser parser = new XmlParser()
        def macro = parser.parseText source
        new Macro(name: macro.'@name', steps: parseSteps(macro.children()))
    }

    def parseSteps(Iterable steps) {
        steps.collect { parse it }
    }

    private static parseCommand(source) {
        new Command(name: source.'@name', target: source.'@target', value: source.'@value')
    }
}
