package dridco.macrolang

class DifferentValuesForMacrosTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ["""
<macro name="sample">
        <command name="echo" target="\$test" />
</macro>
"""]
    }

    @Override
    protected expectedCommands() {
        """
<tr>
    <td>echo</td>
    <td>foo</td>
    <td></td>
</tr>
<tr>
    <td>echo</td>
    <td>bar</td>
    <td></td>
</tr>"""
    }

    @Override
    protected definedCommands() {
        """
<sample test="foo" />
<sample test="bar" />
"""
    }
}
