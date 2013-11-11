package dridco.macrolang

import org.junit.Test

import static dridco.macrolang.SeleniumTestCompiler.compile

abstract class AbstractCommandTest {

    abstract expectedCommands()
    abstract definedCommands()

    def title() { 'a test case' }

    def base() { 'http://localhost' }

    def encoding() { 'UTF-8' }

    @Test void "can parse command"() {
        assert compile("<test title='${title()}' encoding='${encoding()}' base='${base()}'>${definedCommands()}</test>") == """<?xml version="1.0" encoding="${encoding()}"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=${encoding()}" />
<link rel="selenium.base" href="${base()}" />
<title>${title()}</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">${title()}</td></tr>
</thead><tbody>
${expectedCommands()}
</tbody></table>
</body>
</html>"""
    }
}
