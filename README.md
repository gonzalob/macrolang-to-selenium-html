# MacroLang to Selenium HTML #

MacroLang is a custom XML syntax, closely coupled to Selenium IDE's commands
as of now, but adding the possibility of arbitrarily grouping commands under
a given name.

## Motivation ##

The testing team at my workplace has had a great increase in speed and quality
of their work by recording tests in Selenium IDE, but they've hit an obstacle
in maintainability of the generated tests. Plus, they're reluctant to learn a
programming language.

## Context ##

Our project's lifecycle seems to be _good enough_ right now. Developers add
some feature to the application. The testers record some tests to
automatically check its validity. Both commit to the SCM. Developers in Java,
testers in Selenium IDE's HTML syntax. Integration servers then translate the
HTML tests into Java code using
[Selenium HTML to Java](https://github.com/gonzalob/selenium-htmltojava-maven-plugin), 
compiles and runs it with each commit.

Many of the recorded tests have tasks, groups of commands, that are repeated
throughout the test suite. When a feature request means some change to any of
these tasks, testers are required to re-record many tests, or use string
replacement all over the suite, but this is way too error prone.

# Maven Plugin #

This tool integrates to a Maven build lifecycle by declaring a mojo execution
bound to the generate-test-sources phase.

# Implementation #

The language has mainly three constructs: test, command and macro. A command
is translated to exactly one Selenium IDE command, a macro is a group of
commands or other macros, and a test is the actual test to be executed, which
can be composed of commands, macros or both.

## Test ##

```xml
<test name="test-case-001" title="My Test Case" base="http://localhost"
 encoding="ISO-8859-1">
 <login username="johndoe@mailinator.org" password="s3cr3t" />
</test>
```

A test is the sequence of commands and macros that are actually converted to
Selenium IDE's source HTML syntax.
The _encoding_ attribute is optional, and defaults to *UTF-8*

## Commands ##
```xml
<command name="click" target="id=logout" />
```

## Macros ##
```xml
<macro name="login">
 <open-homepage /><!-- another macro -->
 <command name="type" target="id=username" value="$username" />
 <command name="type" target="id=password" value="$password" />
 <command name="click" target="id=submit" />
</macro>
```

Macros are groups of commands, and can receive arbitrary arguments prefixed
with the $ symbol.

## Parametrized Macro Calls ##

```xml
<macro name="login-via">
 <parametrized-macro name="login-$socialNetwork" />
</macro>
```

Parametrized macro calls are calls to macros with parameters in their names.

# Project Links #

 * [Github Project](http://bit.ly/1oc4FqT)
 * [Continuous Integration](http://bit.ly/1nUjQLk) ![Build Status](http://ciarq.dridco.com/jenkins/buildStatus/icon?job=MacroLang Maven Plugin)
 * [Quality Management](http://bit.ly/1s4VY6U)

# License #
This software is licensed under the [GPLv3](http://www.gnu.org/licenses/gpl.txt).

