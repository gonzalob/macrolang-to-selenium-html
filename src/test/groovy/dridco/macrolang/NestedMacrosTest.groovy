package dridco.macrolang

class NestedMacrosTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ["""
<macro name='open-homepage'>
    <command name='open' target='http://localhost/' />
</macro>
""", """
<macro name='my-account'>
    <open-homepage />
    <command name='click' target='id=my-account' />
</macro>
"""
        ]
    }

    @Override
    protected expectedCommands() {
        """
<tr>
    <td>open</td>
    <td>http://localhost/</td>
    <td></td>
</tr>
<tr>
    <td>click</td>
    <td>id=my-account</td>
    <td></td>
</tr>"""
    }

    @Override
    protected definedCommands() {
        '<my-account />'
    }
}
