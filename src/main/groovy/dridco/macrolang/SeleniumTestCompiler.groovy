package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class SeleniumTestCompiler {

    static compile(String source) {
        XmlParser parser = new XmlParser()
        def test = parser.parseText source
        def commands = test.command
        def encoding = test.'@encoding'
        def base = test.'@base'
        def title = test.'@title'
        def rows = new StringBuilder()
        commands.each {
            rows.append parseCommand( it )
        }
        """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}" />
<link rel="selenium.base" href="${base}" />
<title>${title}</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">${title}</td></tr>
</thead><tbody>
${ rows }
</tbody></table>
</body>
</html>"""
	}

    static parseCommand( source ) {
        def value = source.'@value' ?: EMPTY
        """<tr>
    <td>${source.'@name'}</td>
    <td>${source.'@target'}</td>
    <td>${value}</td>
</tr>"""
    }
}
