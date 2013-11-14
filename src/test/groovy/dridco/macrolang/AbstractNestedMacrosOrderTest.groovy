package dridco.macrolang

abstract class AbstractNestedMacrosOrderTest extends AbstractCommandTest {

    abstract boolean reverse()

    @Override
    protected macros() {
        def macros = ["""
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

        if (reverse()) macros
        else macros.reverse()
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
