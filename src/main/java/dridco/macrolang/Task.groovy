package dridco.macrolang

class Task {

    Definition definition
    Map<String, String> context

    @Override
    String toString() {
        render context
    }

    String render(Map<String, String> parent) {
        definition.render context + parent
    }
}
