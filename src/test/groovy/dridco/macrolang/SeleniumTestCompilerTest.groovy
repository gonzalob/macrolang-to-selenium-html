package dridco.macrolang

import static dridco.macrolang.SeleniumTestCompiler.compile

import org.junit.Test

class SeleniumTestCompilerTest {

	@Test
	void "can parse an empty test"() {
		assert compile("<?xml version='1.0' encoding='UTF-8' standalone='yes'?><test title='no commands' encoding='UTF-8' base='http://localhost'></test>") == """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="selenium.base" href="http://localhost" />
<title>no commands</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">no commands</td></tr>
</thead><tbody>

</tbody></table>
</body>
</html>"""
	}

    @Test
    void "can parse simple commands with attributes"() {
        assert compile("<?xml version='1.0' encoding='UTF-8' standalone='yes'?><test title='one command' encoding='UTF-8' base='http://localhost'><command name='waitForElementPresent' target='id=foo' /></test>") == """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="selenium.base" href="http://localhost" />
<title>one command</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">no commands</td></tr>
</thead><tbody>
<tr>
    <td>waitForElementPresent</td>
    <td>id=foo</td>
    <td></td>
</tr>
</tbody></table>
</body>
</html>"""
    }
}
