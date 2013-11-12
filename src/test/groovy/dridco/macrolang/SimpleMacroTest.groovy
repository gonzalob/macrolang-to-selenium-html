package dridco.macrolang

class SimpleMacroTest extends AbstractCommandTest {

    protected macros() {
        ["""
<macro name='logout'>
    <command name='click' target='id=logout' />
</macro>"""]
    }

    @Override
    def expectedCommands() {
        """
<tr>
    <td>click</td>
    <td>id=logout</td>
    <td></td>
</tr>"""
    }

    @Override
    def definedCommands() {
        '<logout />'
    }
}
