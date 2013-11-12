package dridco.macrolang

class MultipleMacrosTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ["""
<macro name='find'>
    <command name='type' target='id=keywords' value='some unique string' />
    <command name='click' target='a[class=result]:first' />
</macro>""", """
<macro name='purchase'>
    <command name='type' target='id=quantity' value='2' />
    <command name='click' target='id=purchase' />
</macro>
"""]
    }

    @Override
    protected expectedCommands() {
        """
<tr>
    <td>type</td>
    <td>id=keywords</td>
    <td>some unique string</td>
</tr>
<tr>
    <td>click</td>
    <td>a[class=result]:first</td>
    <td></td>
</tr>
<tr>
    <td>type</td>
    <td>id=quantity</td>
    <td>2</td>
</tr>
<tr>
    <td>click</td>
    <td>id=purchase</td>
    <td></td>
</tr>"""
    }

    @Override
    protected definedCommands() {
        """
<find />
<purchase />
"""
    }
}
