## to compile

`javac -cp argparse4j-0.9.0.jar Zb32.java` (or `Zb32Alt.java`)

## to run

`java -cp argparse4j-0.9.0.jar:. Zb32` (or `Zb32Alt`)


## Notes

Since this uses a library, a more serious implementation would build a fat jar.
More serious still would be to compile with GraalVM, which I'd like to try.

I tested with Java 17 but there are no fancy features used here. I bet 8 would work, possibly even older.

Argparse4j is quite nice. It mimics the API of Python's `argparse` almost exactly, and is just as easy to use except for the dang _checked exception_ which I could do without.

The style in `Zb32.java` may not seem very Java-y, as the lone class is never instantiated and all code is in static methods. I prefer a functional/procedural style and this is a simple way to accomplish that in Java. For a small program like this I feel it's clearly superior to doing OOP nonsense. `Zb32Alt.java` explores said OOP nonsense. This is the style of Java I learned in high school and college, and which is still commonly written. To me, it feels gross. Where are the _arguments?_ The instance variables act like globals, though since they are `final` this is admittedly safe. It just makes more sense to me to pass values from one part of the program to another _explicitly_ rather than through shared state.

Streams implementation included for a laugh, and because again I prefer functional solutions. Performance is quite similar in quick tests. It seems like a readability negative though.
