package dridco.macrolang

class NewAttributeInNestedMacroTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ["""
<macro name="outer">
    <inner parameter="hello world" />
</macro>
""",
                """
<macro name="inner">
    <command name="echo" target="\$parameter" />
</macro>
"""]

    }

    @Override
    protected expectedCommands() {
        """
<tr>
    <td>echo</td>
    <td>hello world</td>
    <td></td>
</tr>"""
    }

    @Override
    protected definedCommands() {
        """
<outer />
"""
    }
}
