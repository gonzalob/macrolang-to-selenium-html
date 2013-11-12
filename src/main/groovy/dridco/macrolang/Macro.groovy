package dridco.macrolang

import groovy.transform.TupleConstructor

@TupleConstructor
class Macro {

    String name
    Iterable steps

    String toString() {
        steps.join()
    }
}
